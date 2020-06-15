package vip.mate.admin.feign;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.admin.entity.User;
import vip.mate.admin.service.IUserService;
import vip.mate.core.common.api.ApiResult;

@Slf4j
@RestController
@AllArgsConstructor
//@Service(interfaceClass = IUserApi.class)
public class UserApi implements IUserApi {

    private final IUserService userService;

    @Override
    @GetMapping("/api/user-info-by-id")
    public ApiResult<User> userInfoById(Long userId) {
        User user = userService.getById(userId);
        return ApiResult.data(user);
    }

    @Override
    @GetMapping("/api/user-info")
    public ApiResult<User> loadUserByUserName(String userName) {
        User user = userService.getOne(new QueryWrapper<User>().lambda().in(User::getAccount, userName));
        log.info("feign调用：user:{}", user);
        return ApiResult.data(user);
    }
}