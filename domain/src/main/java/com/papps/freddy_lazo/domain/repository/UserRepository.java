package com.papps.freddy_lazo.domain.repository;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Void> saveUser(long userId, Object userData);

}
