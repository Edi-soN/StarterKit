package com.capgemini.brokerageoffice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.brokerageoffice.entity.OfferEntity;
import com.capgemini.brokerageoffice.to.OfferTo;

public class OfferMapper {

	public static OfferTo map(OfferEntity offerEntity) {
		if (offerEntity != null) {
			return new OfferTo(offerEntity.getId(), offerEntity.getSharename(), offerEntity.getSharequantity(),
					offerEntity.getShareprice(), offerEntity.getOffertype());
		}
		return null;
	}

	public static OfferEntity map(OfferTo offerTo) {
		if (offerTo != null) {
			return new OfferEntity(offerTo.getId(), offerTo.getSharename(), offerTo.getSharequantity(),
					offerTo.getShareprice(), offerTo.getOffertype());
		}
		return null;
	}

	public static List<OfferTo> map2To(List<OfferEntity> offerEntities) {
		return offerEntities.stream().map(OfferMapper::map).collect(Collectors.toList());
	}

	public static List<OfferEntity> map2Entity(List<OfferTo> offerTos) {
		return offerTos.stream().map(OfferMapper::map).collect(Collectors.toList());
	}

}
