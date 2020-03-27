package gl51.service

Interface IResizePhoto {
    Photo resize(Photo pictureSource, int dimensionX, int dimensionY);
    Photo addFiligrane(Photo photo);
}

