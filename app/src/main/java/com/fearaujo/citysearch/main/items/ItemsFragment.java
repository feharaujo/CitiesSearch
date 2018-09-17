package com.fearaujo.citysearch.main.items;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fearaujo.arch.executor.impl.ThreadExecutor;
import com.fearaujo.arch.threading.MainThreadImpl;
import com.fearaujo.citysearch.AppApplication;
import com.fearaujo.citysearch.R;
import com.fearaujo.citysearch.main.MainActivity;
import com.fearaujo.data.Repository;
import com.fearaujo.model.City;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemsFragment extends Fragment implements ItemsContract.View {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private EditText mFilterEditText;
    private ItemsAdapter mAdapter;

    private ItemsContract.Presenter mPresenter;
    private TextWatcher mFilterWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPresenter.executeSearch(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public ItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_items, container, false);
        mRecyclerView = view.findViewById(R.id.rvItems);
        mProgressBar = view.findViewById(R.id.loading);
        mFilterEditText = view.findViewById(R.id.etFilter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Repository repository = ((AppApplication) getActivity().getApplication()).getRepository();
        mPresenter = new ItemsPresenter(
                ThreadExecutor.getInstance(),
                MainThreadImpl.getInstance(),
                repository
        );
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.attachView(this);
        mPresenter.onViewCreated();
    }

    @Override
    public void onPause() {
        super.onPause();

        mPresenter.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ItemsAdapter((MainActivity) getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void attachFilterWatcher() {
        mFilterEditText.addTextChangedListener(mFilterWatcher);
    }

    @Override
    public void detachFilterWatcher() {
        mFilterEditText.removeTextChangedListener(mFilterWatcher);
    }

    @Override
    public void errorMessage() {
        Toast.makeText(getContext(), R.string.error_data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateItems(List<City> cities) {
        mAdapter.update(cities);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showItems() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFilter() {
        mFilterEditText.setVisibility(View.VISIBLE);
    }

}
