package com.leeg.contactimp.service;

import com.leeg.contactimp.constants.Constants;
import com.leeg.contactimp.dto.UserDto;
import com.leeg.contactimp.exceptions.TokenExceptions;
import com.leeg.contactimp.exceptions.UserExceptions;
import com.leeg.contactimp.model.ContactModel;
import com.leeg.contactimp.model.UserContactModel;
import com.leeg.contactimp.model.UserModel;
import com.leeg.contactimp.repository.IContactRepo;
import com.leeg.contactimp.repository.IUserContactRepo;
import com.leeg.contactimp.repository.IUserRepo;
import com.leeg.contactimp.util.FileUtil;
import com.leeg.contactimp.util.StringUtil;
import com.leeg.contactimp.util.TokenUtil;
import com.leeg.contactimp.util.UserUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {

    private IUserRepo userRepo;
    private IContactRepo contactRepo;
    private IUserContactRepo userContactRepo;

    public UserDto register(UserDto userDto) {
        UserModel user = UserUtil.getUserModelFromDto(userDto);
        return UserUtil.getUserDtoFromModel(userRepo.save(user));
    }

    public String login(String username, String password) throws UserExceptions.UserNotFound {
        UserModel user = userRepo.findByUsernameAndAndPassword(username, StringUtil.codeString(password));

        if (Objects.isNull(user)){
            String msg = String.format(Constants.USER_NOT_FOUND,username);
            throw new UserExceptions.UserNotFound(msg);
        }

        return TokenUtil.codeString(UserUtil.getUserDtoFromModel(user));
    }

    @Transactional
    public Object uploadCsv(MultipartFile file, String dbColumns, String token) throws TokenExceptions.InvalidToken,
            TokenExceptions.UserNotFoundInToken {
        TokenUtil.verifyToken(token);
        UserDto userDto = TokenUtil.getUserDtoFromToken(token);
        UserModel userModel = userRepo.findByUsernameAndAndPassword(userDto.getUsername(),
                StringUtil.codeString(userDto.getPassword()));
        try {
            List<String> fileContent = FileUtil.readInputStream(file.getInputStream());

            for (int index = 0; index < fileContent.size(); index++) {
                String row = fileContent.get(index);
                String []rowContent = row.split(";");
                String []dbColumnsContent = dbColumns.split(";");

                ContactModel contactModel = new ContactModel();

                for (int dbColIndex = 0; dbColIndex < dbColumnsContent.length; dbColIndex++) {
                    Class<ContactModel> contactModelClass = ContactModel.class;
                    String methodName = dbColumnsContent[dbColIndex];
                    methodName = String.valueOf(methodName.charAt(0)).toUpperCase() + methodName.substring(1);
                    Method method = contactModelClass.getDeclaredMethod("set"+methodName, String.class);
                    method.invoke(contactModel, rowContent[dbColIndex]);
                }
                contactModel = contactRepo.save(contactModel);

                UserContactModel userContactModel = UserContactModel.builder()
                        .contactModel(contactModel).userModel(userModel).build();

                userContactRepo.save(userContactModel);
            }
        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
