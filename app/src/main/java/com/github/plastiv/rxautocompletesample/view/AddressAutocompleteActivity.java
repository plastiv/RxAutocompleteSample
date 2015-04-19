package com.github.plastiv.rxautocompletesample.view;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import com.github.plastiv.rxautocompletesample.R;
import com.github.plastiv.rxautocompletesample.domain.AddressAutocompleteInteractor;
import com.github.plastiv.rxautocompletesample.model.Address;
import com.github.plastiv.rxautocompletesample.storage.ContactStorage;
import com.github.plastiv.rxautocompletesample.storage.ContactStorages;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorage;
import com.github.plastiv.rxautocompletesample.storage.ProfileStorages;
import com.github.plastiv.rxautocompletesample.view.model.AddressAdapter;
import com.github.plastiv.rxautocompletesample.view.model.AddressListItem;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.SearchViewQueryText;
import rx.functions.Func1;
import rx.subscriptions.CompositeSubscription;

public class AddressAutocompleteActivity extends Activity {
    private static final String TAG = "AutocompleteActivity";

    private ListView addressListView;
    private AddressAdapter addressAdapter;
    private SearchView searchView;
    private CompositeSubscription compositeSubscription;
    private AddressAutocompleteInteractor autocompleteInteractor;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_autocomplete);
        addressListView = (ListView) findViewById(R.id.addressListView);
        addressAdapter = new AddressAdapter(this);
        addressListView.setAdapter(addressAdapter);
        injectDependencies();
    }

    private void injectDependencies() {
        // real app would use real DI like dagger
        ProfileStorage profileStorage = ProfileStorages.getInstance();
        ContactStorage contactStorage = ContactStorages.getInstance();
        autocompleteInteractor = new AddressAutocompleteInteractor(profileStorage, contactStorage);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_address_autocomplete, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        item.expandActionView(); // get focus
        searchView = (SearchView) item.getActionView();

        return true;
    }

    @Override protected void onResume() {
        super.onResume();
        // resubscribe onresume
        compositeSubscription = new CompositeSubscription();
        Observable<String> search = SearchViewQueryText.change(searchView);
        autocompleteInteractor.autocomplete(search)
                              .map(new Func1<Address, AddressListItem>() {
                                  @Override public AddressListItem call(Address address) {
                                      return AddressListItem.from(address);
                                  }
                              })
                              .toList()
                              .observeOn(AndroidSchedulers.mainThread())
                              .subscribe(new Subscriber<List<AddressListItem>>() {
                                  @Override public void onCompleted() {
                                      Log.v(TAG, "onCompleted");
                                  }

                                  @Override public void onError(Throwable e) {
                                      Log.e(TAG, "onError", e);
                                  }

                                  @Override public void onNext(List<AddressListItem> addressListItems) {
                                      addressAdapter.setList(addressListItems);
                                  }
                              });
    }

    @Override protected void onPause() {
        super.onPause();
        // in our case unsubscribe means that we cancel ongoing operation including async call to google servers and
        // drop operation result on the floor. This makes sure that background thread doesn't hold reference to the Activity.
        // When activity will be restarted by user or by system new call will be requested. This behaviour suits our
        // need in case of autocomplete. If you need to persist api call or response result you should save/restore
        // state instead of the cancelling operation.
        compositeSubscription.unsubscribe();
    }
}
