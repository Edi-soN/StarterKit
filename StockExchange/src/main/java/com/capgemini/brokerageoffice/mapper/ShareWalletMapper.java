package com.capgemini.brokerageoffice.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.brokerageoffice.entity.ShareWalletEntity;
import com.capgemini.brokerageoffice.to.ShareWalletTo;

public class ShareWalletMapper {

	public static ShareWalletTo map(ShareWalletEntity shareWalletEntity) {
		if (shareWalletEntity != null) {
			return new ShareWalletTo(shareWalletEntity.getId(), shareWalletEntity.getSharename(),
					shareWalletEntity.getSharequantity());
		}
		return null;
	}

	public static ShareWalletEntity map(ShareWalletTo shareWalletTo) {
		if (shareWalletTo != null) {
			return new ShareWalletEntity(shareWalletTo.getId(), shareWalletTo.getSharename(),
					shareWalletTo.getSharequantity());
		}
		return null;
	}

	public static List<ShareWalletTo> map2To(List<ShareWalletEntity> shareWalletEntities) {
		return shareWalletEntities.stream().map(ShareWalletMapper::map).collect(Collectors.toList());
	}

	public static List<ShareWalletEntity> map2Entity(List<ShareWalletTo> shareWalletTos) {
		return shareWalletTos.stream().map(ShareWalletMapper::map).collect(Collectors.toList());
	}

}
