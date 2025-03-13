package com.resource.storage.domain.service.impl;

import com.resource.common.utils.GsonUtil;
import com.resource.core.domain.Rights;
import com.resource.core.service.IResourceBaseService;
import com.resource.core.service.IResourceHistoryService;
import com.resource.core.service.ResourceCoreService;
import com.resource.storage.domain.models.Storage;
import com.resource.storage.domain.models.UploadInfo;
import com.resource.storage.domain.service.IResourceStorageService;
import com.resource.storage.domain.service.IStorageService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ResourceStorageService extends ResourceCoreService<Storage> implements IResourceStorageService<Storage> {
    private final IStorageService storageService;

    public ResourceStorageService(IResourceBaseService<Storage> resourceBaseService,
                                  IResourceHistoryService<Storage> resourceHistoryService,
                                  IStorageService storageService) {
        super(resourceBaseService, resourceHistoryService);
        this.storageService = storageService;
    }

    @Override
    public Mono<UploadInfo> getUploadSts(String buckets, Storage resourceReq) {
        return storageService.generateUploadInfo(buckets, resourceReq.getTitle(), 60)
                .doOnNext(uploadInfo -> {
                    resourceReq.create();
                    resourceReq.setUri(uploadInfo.getUri());
                    Rights rights = new Rights();
                    rights.setAccessKey(uploadInfo.getSts().getAccessKeyId());
                    rights.setSecretKey(uploadInfo.getSts().getAccessKeySecret());
                    rights.setToken(uploadInfo.getSts().getSecurityToken());
                    resourceReq.setRights(rights);
                    resourceReq.setIdentifiers(GsonUtil.toMap(uploadInfo.getOssMetadata()));
                    super.save(resourceReq);
                });
    }

    @Override
    public Mono<Void> handleCallback(String objectKey) {
        return null;
    }
}
