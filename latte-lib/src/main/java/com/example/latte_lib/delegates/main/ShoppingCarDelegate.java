package com.example.latte_lib.delegates.main;

import android.os.Bundle;
import android.view.View;

import com.example.latte_core.delegates.main.BaseContentDelegate;
import com.example.latte_lib.R;

public class ShoppingCarDelegate extends BaseContentDelegate implements View.OnClickListener {

    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDZ647y2giux2bc5QZhmHusBtKrPupBjcKO27vjlDIkJHiYUOHPXRYAVLELVuBFYldDuDlxc7yydvkwpGtMx/pF5QxfkhBIpzYheqngeC9DUeO5xU9gebWNnxGrZUrZ1ds7qH4x37HJIusyl6OLUgqJGc5I6s+h8zVoHUZy/jHWpdRWCR7ngKjmTvOO4XJGY5GL9M9LElohiv8bLh6KPx/qgDciBvlOkOsQqQgBYv5C2NmU9wqcKfvA/+Jo+qcUGU6IGPTnnzR/uugvWkaKb1OLZrbjy47wR/tpzLyAuUA0I8QkwVOClipiyXDlS7XpCOUCmUEQQAqJbvd9l+ext8AtAgMBAAECggEAN6cp5al7KC+yfF1dCNWpV+CSMruplb4/Xr6NOGyjdYl+5t9ZG90Y/lKiuFOiiPW5dYj52piFK0qpDxwebwhvSSRayZmC4Rsa/ufVtIbdAyNJtirGv8u1awqht5TxzboI+C5qtA/91nE9gbkqQi1clXDqtsKI7kIvdHxtfnaegcN1V5fDYU5eMyGgJZTtWyCZ4e9ZWo/5oYBF6uTpRWyCQBBMLj5ceRu+QtLT86MB6add0CfR5dqQFWf4FT5uz0GHEaXZLi15tDwOqOxr8zfBP9PE+PyVw0YZTrsiW58sNa86nao9ZlETYFvN40v3XNd5Voz1tiyjKYicRSO3t3ge3QKBgQDsoDxg1pLHvQTbODCsyhOKoNUZZFr9WHGfDNX3HR8tniiH4ElLF0cqXrPdayNJY9AYZzlhiO4nRINOHm1I/UQIhuEssjP9dhdaDV/pvSKHGu9xLLKe7Ge27RMfulTFcc6o7BZ5Ss7Df39eWjLGwtiYomnE9Fa8nCpC6IQMD6qX/wKBgQDrwz4sh2GNIr7jC82HIA0UJAzNC2uZ4jNPVeYrdYpoZqRu9jQl7SHnBFKEl/KAgTkQ2D6xK+IICE0zprri0AZ5VWfEO0BrjScg2NV4yGjzS9FVw43/W8/M/Ibv7L7RKlHMRJHIPLo05UpQOnZFsZv11WurHCKsKNUVCg6wtguH0wKBgQDgZZycoSAVNxWR5xmR4xjhEGDGcbswIl2t3XhTnlRg6Gm7YWFgSrT8Y07jHaUbBgLkAZATBM4Egx6aSJSO8U9BXtfPkTdomELQ7XoVqX1C7rI/e4nw2HQKr4zBO+0ESUcYPruZTu4By7k3+wXIJCNPrEXtt2FCAD67sND4tyqqdQKBgC9rwA+401hOCtMkRpzNR5QrhB+iEmyLYQKuJnPLWqGRM7XV87tFLTKIXgipf291joLi3AlK6FXxRYYEHUcNzJ+PHCYOPPDlHV+v/ZAjz2hQcbSsdt1MHEoA6StNFTyBJJ7Pk/sQq+uDyyTD/ZIbHWGfId7y885pECMBON82Xq9dAoGBAJBxXFWmyaid0y0yT1SNQcXCYhBbKVxlsAh/mpK+RX55W2VhUDDYyHj6XeNQJIAKYSl1wzyemQQZMR+TIQKfEyU2vO8txci+5yUWC4bhD3zbH9KNRPzsVWDi44QGwGmQAfRcA+/unrWYjfsJyLgl1lT2SuE2BfJuSLGuZ7wWuRuM";
    public static final String APPID = "2016060501482714";

    @Override
    protected void onBinderView(Bundle savedInstanceState, View rootView) {
        rootView.findViewById(R.id.btn_pay).setOnClickListener(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_shopping_cart;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_pay) {

        }
    }
}
