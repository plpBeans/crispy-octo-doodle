package vip.hengnai.wine.eventbus;

import vip.hengnai.wine.entity.PersonAddressEntity;
import vip.hengnai.wine.entity.ShopEntity;

/**
 * AUTHOR：dell
 * TIME:2019/8/30 2019/8/30
 * DESCRIPTION:ShopEvent
 * @author hua
 */
public class TakeAwayEvent {
    private PersonAddressEntity personAddressEntity;

    public PersonAddressEntity getPersonAddressEntity() {
        return personAddressEntity;
    }

    public TakeAwayEvent setPersonAddressEntity(PersonAddressEntity personAddressEntity) {
        this.personAddressEntity = personAddressEntity;
        return this;
    }

    public TakeAwayEvent(PersonAddressEntity personAddressEntity) {
        this.personAddressEntity = personAddressEntity;
    }


}
