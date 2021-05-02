package io.github.bhhan.example.shop.usecase;

import io.github.bhhan.example.shop.domain.Menu;
import io.github.bhhan.example.shop.domain.MenuRepository;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import io.github.bhhan.example.shop.usecase.dto.MenuDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by hbh5274@gmail.com on 2021-04-23
 * Github : http://github.com/bhhan5274
 */

@Transactional
@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    public Long addMenu(MenuDto.MenuReq menuReq){
        return menuRepository.save(menuMapper.fromMenuReq(menuReq)).getId();
    }

    public void validateOrder(Long menuId, String menuName, List<OptionGroup> optionGroups){
        final Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid menuId"));

        menu.validateOrder(menuName, optionGroups);
    }
}
