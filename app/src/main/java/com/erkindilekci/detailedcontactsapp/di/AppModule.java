package com.erkindilekci.detailedcontactsapp.di;

import android.content.Context;

import androidx.room.Room;

import com.erkindilekci.detailedcontactsapp.data.repository.ContactRepository;
import com.erkindilekci.detailedcontactsapp.data.room.ContactDao;
import com.erkindilekci.detailedcontactsapp.data.room.ContactDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public ContactDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, ContactDatabase.class, "contacts_db").build();
    }

    @Provides
    @Singleton
    public ContactDao provideDao(ContactDatabase database) {
        return database.contactDao();
    }

    @Provides
    @Singleton
    public ContactRepository provideRepository(ContactDao dao) {
        return new ContactRepository(dao);
    }
}
