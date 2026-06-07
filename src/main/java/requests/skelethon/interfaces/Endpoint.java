package requests.skelethon.interfaces;

import lombok.AllArgsConstructor;
import lombok.Getter;
import models.*;

@AllArgsConstructor
@Getter
public enum Endpoint {

    ADMIN_USER(
            "/api/v1/admin/users",
            CreateUserByAdminRequest.class,
            CreateUserByAdminResponse.class
    ),

    LOGIN(
            "/api/v1/auth/login",
            AuthorizationRequest.class,
            AuthorizationResponse.class
    ),

    CUSTOMER_PROFILE(
            "/api/v1/customer/profile",
            ChangeNameByUserRequest.class,
            GetUserInfoResponse.class
    ),

    GET_INFO(
            "/api/v1/customer/profile",
            BaseModel.class,
            GetUserInfoResponse.class
    ),

    ACCOUNTS(
            "/api/v1/accounts",
            BaseModel.class,
            CreateUserAccountsResponse.class
    ),

    ACCOUNTS_DEPOSITE(
            "/api/v1/accounts/deposit",
            DepositeRequest.class,
            DepositeResponse.class
    ),

    CUSTOMER_ACCOUNTS(
            "/api/v1/customer/accounts",
            BaseModel.class,
            CheckUserAccountsResponse.class
    ),

    ACCOUNTS_TRANSFER(
            "/api/v1/accounts/transfer",
            TransferRequest.class,
            TransferResponse.class
    );

    private final String url;
    private final Class<? extends BaseModel> requestModel;
    private final Class<? extends BaseModel> responseModel;
}
