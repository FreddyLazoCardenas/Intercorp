package com.papps.freddy_lazo.intercorp.model.mapper;

import com.papps.freddy_lazo.domain.model.User;
import com.papps.freddy_lazo.intercorp.model.UserModel;

public class UserModelMapper {

    public static UserModel transform(User model){
        if(model == null)
            return null;
        return new UserModel(model.getName(), model.getLastName(),model.getAge(),model.getBirthDate());
    }
}
