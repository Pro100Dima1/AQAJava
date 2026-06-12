package requests.skelethon.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.*;

@AllArgsConstructor
@Getter
public enum Endpoint {

    ADMIN_USER(
            "admin/users",
            CreateUserByAdminRequest.class,
            CreateUserByAdminResponse.class
    ),

    LOGIN(
            "auth/login",
            AuthorizationRequest.class,
            AuthorizationResponse.class
    ),

    CUSTOMER_PROFILE(
            "customer/profile",
            ChangeNameByUserRequest.class,
            GetUserInfoResponse.class
    ),

    GET_INFO(
            "customer/profile",
            BaseModel.class,
            GetUserInfoResponse.class
    ),

    ACCOUNTS(
            "accounts",
            BaseModel.class,
            CreateUserAccountsResponse.class
    ),

    ACCOUNTS_DEPOSITE(
            "accounts/deposit",
            DepositeRequest.class,
            DepositeResponse.class
    ),

    CUSTOMER_ACCOUNTS(
            "customer/accounts",
            BaseModel.class,
            CheckUserAccountsResponse.class
    ),

    ACCOUNTS_TRANSFER(
            "accounts/transfer",
            TransferRequest.class,
            TransferResponse.class
    ),

    DELETE_USER(
            "admin/users",
            BaseModel.class,
            BaseModel.class
    );

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}
