package com.Project.Blogging.Platform.API.Model.DataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class SignUpOutput {
    private boolean signUpStatus;
    private String signUpStatusMessage;
}
