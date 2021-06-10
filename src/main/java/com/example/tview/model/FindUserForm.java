package com.example.tview.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserForm {
    private String UserName;
    private String Name;
    private String email;
    private String phone;
}
