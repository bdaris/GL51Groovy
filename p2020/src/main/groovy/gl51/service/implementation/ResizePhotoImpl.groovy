package gl51.service.implementation

import gl51.data.Photo
import gl51.service.ResizePhoto

import javax.inject.Inject

class ResizePhotoImpl implements ResizePhoto{
    @Inject
    Photo photo

    @Inject
    RecupPhotoImpl recupPhoto

    @Inject
    StoreCloudImpl storeCloud

    @Override
    void getAndResizePhoto() {
        //récupération de l'image
        Photo source = RecupPhotoImpl.fetchPhoto()

        //redimension au format 1024x1024
        Photo newPhoto = resize(source, 1024, 1024)

        //Création du thumbail
        Photo thumbnail = resize(source, 50, 50)

        //Ajout du filigrane
        Photo logo = addFiligrane(thumbnail)

        //Stockage dans le cloud
        storeCloud.uploadToCloud(newPhoto)
        storeCloud.uploadToCloud(logo)

        //Mise à jour de la base de données
        updateDataDase.updatePhotoInBD(newPhoto.getX(), new_image.getY(), newPhoto.getName())
    }
}