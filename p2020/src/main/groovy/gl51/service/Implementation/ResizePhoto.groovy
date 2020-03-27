package gl51.service

import gl51.data.Image
import gl51.service.Implementation.StoreCloudImpl
import gl51.service.Implementation.RecupPhotoImpl
import gl51.service.Implementation.UpdateBD
import gl51.service.IResizePhoto
import javax.inject.Inject

@Singleton
class ResizePhoto implements IResizePhoto {
    @Inject
    RecupPhotoImpl recupPhoto;

    @Inject
    StoreCloudImpl storeCloud;

    @Inject
    UpdateBD updateDataDase;

    @Override
    Photo resize(Picture pictureSource, int dimensionX, int dimensionY) {
        return null;
    }

    @Override
    Photo addFiligrane(Photo photo){
        return null;
    }

    Photo getResizePhoto(){
        //récupération de l'image
        Photo source = recupPhoto.fetchPhoto();

        //redimension au format 1024x1024
        Photo newPhoto = resize(source,1024,1024);

        //Création du thumbail
        Photo thumbnail = resize(source,50,50);

        //Ajout du filigrane
        Photo logo = addFiligrane(thumbnail);

        //Stockage dans le cloud
        storeCloud.uploadToCloud(newPhoto);
        storeCloud.uploadToCloud(logo);

        //Mise à jour de la base de données
        updateDataDase.updatePhotoInBD(newPhoto.getX(),new_image.getY(),newPhoto.getName())
    }

}
