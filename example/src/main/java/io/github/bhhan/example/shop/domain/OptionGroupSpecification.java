package io.github.bhhan.example.shop.domain;

import io.github.bhhan.example.common.domain.shop.Option;
import io.github.bhhan.example.common.domain.shop.OptionGroup;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by hbh5274@gmail.com on 2021-04-22
 * Github : http://github.com/bhhan5274
 */

@Entity
@Table(name = "OPTION_GROUP_SPECS")
@Getter
@NoArgsConstructor
public class OptionGroupSpecification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OPTION_GROUP_SPEC_ID")
    private Long id;

    private String name;
    private boolean exclusive;
    private boolean basic;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OPTION_GROUP_SPEC_ID")
    private List<OptionSpecification> optionSpecs = new ArrayList<>();

    public static OptionGroupSpecification basic(String name, boolean exclusive,
                                                 OptionSpecification... options) {
        return new OptionGroupSpecification(name, exclusive, true, options);
    }

    public static OptionGroupSpecification additive(String name, boolean exclusive,
                                                    OptionSpecification... options) {
        return new OptionGroupSpecification(name, exclusive, false, options);
    }

    private OptionGroupSpecification(String name, boolean exclusive, boolean basic,
                                     OptionSpecification... options) {
        this(null, name, exclusive, basic, Arrays.asList(options));
    }

    @Builder
    public OptionGroupSpecification(Long id, String name, boolean exclusive,
                                    boolean basic,
                                    List<OptionSpecification> options) {
        this.id = id;
        this.name = name;
        this.exclusive = exclusive;
        this.basic = basic;
        this.optionSpecs.addAll(options);
    }

    public boolean isSatisfiedBy(OptionGroup optionGroup) {
        return isSatisfied(optionGroup.getName(), optionGroup.getOptions());
    }

    private boolean isSatisfied(String groupName, List<Option> options) {
        if (!this.name.equals(groupName)) {
            return false;
        }

        final List<Option> satisfied = this.optionSpecs
                .stream()
                .flatMap(spec -> options
                        .stream()
                        .filter(spec::isSatisfiedBy))
                .collect(toList());

        if (satisfied.isEmpty()) {
            return false;
        }

        if (exclusive && satisfied.size() > 1) {
            return false;
        }

        return satisfied.size() == options.size();
    }
}
