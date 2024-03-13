package com.yasemin.utility;

import com.yasemin.utility.enums.ERole;
import org.springframework.stereotype.Service;

@Service
public class MailCheckManager {
    public ERole checkMail(String mail) {
        int index = mail.indexOf("@");
        if (mail.substring(index).equals("@vvtadmin.com")) {
            return ERole.ADMIN;
        } else if (mail.substring(index).equals("@vvt.com")) {
            return ERole.MODERATOR;
        }else {
            return ERole.USER;
        }
    }
}
