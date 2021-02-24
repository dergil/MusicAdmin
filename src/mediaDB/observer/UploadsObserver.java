package mediaDB.observer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import mediaDB.domain_logic.MediaFileRepository;
import mediaDB.domain_logic.file_interfaces.Uploadable;
import mediaDB.domain_logic.observables.ChangeInDomainLogic;
import mediaDB.domain_logic.observables.UploadsObservable;
import mediaDB.observer.Observer;

//TODO: führt zu Schwierigkeiten bei den Simulationen; erstmal ausgesetzt, bei Zeitmangel mit sout im MediaRepo ersetzen
public class UploadsObserver {//implements Observer {
//    UploadsObservable uploadsObservable;
//    MediaFileRepository mediaFileRepository;
//
//    public UploadsObserver(UploadsObservable uploadsObservable, MediaFileRepository mediaFileRepository) {
//        this.uploadsObservable = uploadsObservable;
//        this.mediaFileRepository = mediaFileRepository;
//    }
//
//    @Override
//    public synchronized void update() {
//        ChangeInDomainLogic changeInDomainLogic = uploadsObservable.getChangeInDomainLogic();
//        Uploadable uploadable = mediaFileRepository.findByAddress(changeInDomainLogic.getAddress());
//        System.out.println(changeInDomainLogic.getType() + " " + uploadable.toString());
//    }
}
