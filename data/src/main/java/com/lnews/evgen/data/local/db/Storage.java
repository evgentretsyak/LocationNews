package com.lnews.evgen.data.local.db;

import android.annotation.SuppressLint;
import com.lnews.evgen.data.local.db.dao.CategoryDao;
import com.lnews.evgen.data.local.db.dao.DescriptionDao;
import com.lnews.evgen.data.local.db.storage.DBStorage;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Storage {
    private final CategoryDao categoryDao;
    private final DescriptionDao descriptionDao;
    private final DBStorage storage;

    @Inject
    Storage(DBStorage storage) {
        this.categoryDao = storage.getCategoryDao();
        this.descriptionDao = storage.getDescriptionDao();
        this.storage = storage;
    }

    private void insertCategoryList(List<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            categoryDao.insert(categories.get(i));
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void insertDescriptionList(String category, List<Article> articles) {
        for (int i = 0; i < articles.size(); i++) {
            articles.get(i).setCategory(category);
            descriptionDao.insert(articles.get(i));
        }
    }

    private void clearTables(){
        categoryDao.nukeTable();
        descriptionDao.nukeTable();
    }

    public Single<List<Category>> getCategories() {
        return categoryDao.getAll();
    }

    public Completable insertCategories(List<Category> categories) {
        return Completable.fromAction(() -> insertCategoryList(categories));
    }

    public Completable removeCategory(Category category) {
        return Completable.fromAction(() -> categoryDao.delete(category));
    }

    public Completable insertCategory(Category category) {
        return Completable.fromAction(() -> categoryDao.insert(category));
    }

    public Single<RootObject> getDescriptionsByKey(String category) {
        return Single.create(emitter -> {
            RootObject rootObject = new RootObject();
            rootObject.setArticles(descriptionDao.getAllByKey(category));
            emitter.onSuccess(rootObject);
        });
    }

    public Completable insertDescriptions(String category, List<Article> articles) {
        return Completable.fromAction(() -> insertDescriptionList(category, articles));
    }

    public Completable clearDescriptionByKey(String category){
        return Completable.fromAction(() -> descriptionDao.clearByKey(category));
    }

    public Completable clearDB(){
        return Completable.fromAction(this::clearTables);
    }
}
