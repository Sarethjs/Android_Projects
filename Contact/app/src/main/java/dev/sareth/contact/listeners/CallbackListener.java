package dev.sareth.contact.listeners;

import java.util.List;

import dev.sareth.contact.models.Comment;
import dev.sareth.contact.models.Contact;
import dev.sareth.contact.models.ContactLocation;

public interface CallbackListener {

    interface item {
        void itemReceived(Object object);
        void itemNotReceived(String error);
    }

    interface items{
        void itemsReceived(List<Contact> items);
        void itemsNotReceived(String error);
    }

    interface comments{
        void itemsReceived(List<Comment> items);
        void itemsNotReceived(String error);
    }

    interface location{
        void locationReceived(ContactLocation contactLocation);
    }


}
