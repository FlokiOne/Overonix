package com.Overonix.Test.presentation.dto;

import com.Overonix.Test.data.entity.EAddress;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RsUserAddress {
    private String userName;
    private List<EAddress> addressList;

}
