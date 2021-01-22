package com.bmby.rozetkatestproject.framework.network.mappers

import com.bmby.rozetkatestproject.framework.network.models.ImageUrlsNetworkModel
import com.bmby.rozetkatestproject.logic.domain.models.ImageUrlsModel
import com.bmby.rozetkatestproject.logic.domain.util.EntityMapper
import javax.inject.Inject

class UrlsMapper
@Inject
constructor() : EntityMapper<ImageUrlsNetworkModel, ImageUrlsModel> {

    override fun mapFromEntity(entity: ImageUrlsNetworkModel): ImageUrlsModel {
        return ImageUrlsModel(
            raw = entity.raw.toString(),
            full = entity.full.toString(),
            regular = entity.regular.toString(),
            small = entity.small.toString(),
            thumb = entity.thumb.toString(),
        )
    }

    override fun mapToEntity(domainModel: ImageUrlsModel): ImageUrlsNetworkModel {
        return ImageUrlsNetworkModel(
            raw = domainModel.raw,
            full = domainModel.full,
            regular = domainModel.regular,
            small = domainModel.small,
            thumb = domainModel.thumb,
        )
    }

}