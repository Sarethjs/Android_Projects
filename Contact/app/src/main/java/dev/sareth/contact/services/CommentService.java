package dev.sareth.contact.services;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import dev.sareth.contact.listeners.CallbackListener;
import dev.sareth.contact.models.Comment;
import dev.sareth.contact.models.Contact;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentService {

    private static final iCommentService service =
            iContacService.RETROFIT.create(iCommentService.class);

    public static void create(Comment comment, CallbackListener.item listener){

        service.create(comment).enqueue(new Callback<Comment>() {
            @Override
            public void onResponse(@NonNull Call<Comment> call,
                                   @NonNull Response<Comment> response) {
                if (response.isSuccessful()){
                    listener.itemNotReceived("Comment saved");
                } else{
                    listener.itemNotReceived("Comment not saved");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Comment> call,
                                  @NonNull Throwable t){
                listener.itemNotReceived("Commetn not saved: Unknown error");
            }
        });
    }

    public static void find(Contact contact, CallbackListener.comments listener){

        service.getComments().enqueue(new Callback<List<Comment>>() {
            List<Comment> comments = new ArrayList<>();
            @Override
            public void onResponse(@NonNull Call<List<Comment>> call,
                                   @NonNull Response<List<Comment>> response) {
                if (response.isSuccessful()) {
                    comments = response.body();

                    if (comments == null)
                        return;

                    for (int i = 0; i < comments.size(); i++){
                        if (comments.get(i).getPubId() != contact.getId()) comments.remove(i);
                    }
                    listener.itemsReceived(comments);

                } else listener.itemsNotReceived("Items not loaded");
            }

            @Override
            public void onFailure(@NonNull Call<List<Comment>> call,
                                  @NonNull Throwable t) {
                listener.itemsNotReceived("Items not loaded: " + t);
            }
        });
    }
}
