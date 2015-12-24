package korektur.friendslist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vk.sdk.api.model.VKApiUserFull;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Created by korektur
 * 23/12/15.
 */
public class FriendListRecyclerViewAdapter extends RecyclerView.Adapter<FriendListItemViewHolder> {

    private final LayoutInflater layoutInflater;
    private List<VKApiUserFull> friendsList;

    public FriendListRecyclerViewAdapter(Activity context) {
        this.layoutInflater = context.getLayoutInflater();
        this.friendsList = Collections.emptyList();
    }

    @Override
    public FriendListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = layoutInflater.inflate(R.layout.friend_list_item, parent, false);
        return new FriendListItemViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(FriendListItemViewHolder holder, int position) {
            holder.loadNewFriend(layoutInflater.getContext(), friendsList.get(position));
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    public void setFriendsList(@NotNull List<VKApiUserFull> friendsList) {
        this.friendsList = friendsList;
    }
}
