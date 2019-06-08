package com.papps.freddy_lazo.domain.repository;

import io.reactivex.Observable;

public interface UserRepository {

    Observable<Void> saveUser(String userId, Object userData);

    Observable<Boolean> isUserRegister(String userId);

}
