package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

public class RemoveDescriptionUseCase extends CompletableUseCase<String> {

    @Inject
    RemoveDescriptionUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Completable buildUseCase(String category) {
        return repository.removeNewsByCategoryFromDB(category);
    }
}
