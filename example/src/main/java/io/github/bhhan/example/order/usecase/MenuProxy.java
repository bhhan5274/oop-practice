package io.github.bhhan.example.order.usecase;

import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.web.MenuController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Component
@RequiredArgsConstructor
public class MenuProxy {
    private final MenuController menuController;

    boolean validateOrder(Long menuId, String menuName, List<OptionGroup> optionGroups){
        return menuController.validateOrder(menuId, menuName, optionGroups);
    }
}
