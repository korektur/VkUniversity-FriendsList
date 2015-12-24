package korektur.friendslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vk.sdk.api.model.VKApiUserFull;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by korektur
 * 13/12/15.
 */
public class FriendListItemViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.friendImageView)
    ImageView imageView;
    @Bind(R.id.friendNameTextView)
    TextView nameView;
    @Bind(R.id.friendIsOnlineTextView)
    TextView isOnlineView;

    public FriendListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void loadNewFriend(Context context, VKApiUserFull newFriend) {
        Picasso.with(context).load(newFriend.photo_100).into(imageView);
        nameView.setText(buildFullName(newFriend));
        isOnlineView.setText(getIsOnlineTextId(newFriend.online, context));
    }

    private static String buildFullName(VKApiUserFull user) {
        return user.first_name + " " + user.last_name;
    }

    private static String getIsOnlineTextId(boolean isOnline, Context context) {
        int resId = isOnline ? R.string.friend_is_online_text : R.string.friend_is_offline_text;
        return context.getString(resId);
    }
}
