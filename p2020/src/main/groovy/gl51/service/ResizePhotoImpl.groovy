package gl51.service

import gl51.data.Photo

import javax.inject.Inject

class ResizePhotoImpl implements gl51.service.ResizePhoto {
    @Inject
    Photo photo
    @Override
    void getAndResizePhoto(){}
}