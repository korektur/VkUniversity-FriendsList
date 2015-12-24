package korektur.friendslist;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiUserFull;
import com.vk.sdk.api.model.VKUsersArray;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by korektur
 * 24/12/15.
 */
public class FriendListFragment extends Fragment {

    private static final String FRIEND_LIST_REQUEST_PARAMS = "photo_100,online";

    @Bind(R.id.friendListRecyclerView)
    RecyclerView recyclerView;

    public FriendListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.friend_list_fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new FriendListRecyclerViewAdapter(getActivity()));
        fetchFriends();
    }

    private void onCompleteResponse(VKResponse response) {
        if (response.parsedModel instanceof VKUsersArray) {
            List<VKApiUserFull> friends = (VKUsersArray) response.parsedModel;
            if (recyclerView.getAdapter() != null) {
                ((FriendListRecyclerViewAdapter) recyclerView.getAdapter()).setFriendsList(friends);
            }
        }
    }


    private void fetchFriends() {
        VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS, FRIEND_LIST_REQUEST_PARAMS))
                .executeWithListener(new VKRequest.VKRequestListener() {
                    @Override
                    public void onError(VKError error) {
                        super.onError(error);
                    }

                    @Override
                    public void onComplete(VKResponse response) {
                        super.onComplete(response);
                        onCompleteResponse(response);
                    }
                });
    }
}
