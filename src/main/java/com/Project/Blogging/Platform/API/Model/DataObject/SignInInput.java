package com.Project.Blogging.Platform.API.Model.DataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignInInput {
    private String email;
    private String password;
}
