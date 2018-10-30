package parkingwatcher.repository;

import com.mongodb.*;
import parkingwatcher.model.parkedvehicle.ParkedVehicle;
import parkingwatcher.util.Converter;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MongoDataAccess implements IDataAccess {
    private static final String _dbName = "db_pw";
    private static final String _collectionName = "parked_vehicles";


    public List<ParkedVehicle> getParkedVehiclesByTypeAndStatusParked(String typeVehicle) {
        List<ParkedVehicle> list = new LinkedList<>();
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = getCollection(mongoClient);
        DBObject parked = new BasicDBObject("typeVehicle", typeVehicle)
                .append("status", "PARKED");
        DBCursor dbCursor = collection.find(parked);

        if(!dbCursor.hasNext()) {
            return list;
        }
        Converter converter = new Converter();
        Iterator iterator = dbCursor.iterator();
        while (iterator.hasNext()) {
            list.add(converter.DBObjectToParkedVehicle((DBObject) iterator.next()));
        }
        return list;
    }

    public ParkedVehicle updateParkedVehicle(ParkedVehicle parkedVehicle) {
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = getCollection(mongoClient);
        DBObject filter = new BasicDBObject("idVehicle", parkedVehicle.getIdVehicle());
        DBObject update = new BasicDBObject("idVehicle", parkedVehicle.getIdVehicle())
                .append("typeVehicle", parkedVehicle.getTypeVehicle())
                .append("entryDate", parkedVehicle.getEntryDate())
                .append("status", parkedVehicle.getStatus())
                .append("exitDate", parkedVehicle.getExitDate())
                .append("paidValue", parkedVehicle.getPaidValue())
                .append("engineCapacity", parkedVehicle.getPaidValue());


        collection.update(filter, update);

        mongoClient.close();

        return parkedVehicle;
    }

    public ParkedVehicle searchParkedVehicleByIdVehicleAndStatusParked(String idVehicle) {
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = getCollection(mongoClient);
        DBObject parked = new BasicDBObject("idVehicle", idVehicle)
                .append("status", "PARKED");
        DBCursor dbCursor = collection.find(parked);

        if(!dbCursor.hasNext()) {
            return null;
        }

        DBObject found = dbCursor.iterator().next();
        Converter converter = new Converter();

        mongoClient.close();

        return converter.DBObjectToParkedVehicle(found);
    }

    public List<ParkedVehicle> fetchAllVehiclesParked() {
        System.out.println("fetchAllVehiclesParked");
        List<ParkedVehicle> list = new LinkedList<>();
        MongoClient mongoClient = new MongoClient();
        DBCollection collection = getCollection(mongoClient);
        DBObject parked = new BasicDBObject("status", "PARKED");
        DBCursor dbCursor = collection.find(parked);

        if(!dbCursor.hasNext()) {
            return list;
        }
        Converter converter = new Converter();
        Iterator iterator = dbCursor.iterator();
        while (iterator.hasNext()) {
            list.add(converter.DBObjectToParkedVehicle((DBObject) iterator.next()));
        }
        return list;
    }

    public ParkedVehicle insertParkedVehicle(ParkedVehicle pv) {
        MongoClient mongoClient = new MongoClient();
        DBObject parked = new BasicDBObject("typeVehicle", pv.getTypeVehicle())
                .append("idVehicle", pv.getIdVehicle())
                .append("entryDate", pv.getEntryDate())
                .append("exitDate", pv.getExitDate())
                .append("status", pv.getStatus())
                .append("paidValue", pv.getPaidValue())
                .append("engineCapacity", pv.getEngineCapacity());

        DBCollection collection = getCollection(mongoClient);
        collection.insert(parked);

        mongoClient.close();

        return pv;
    }

    private DBCollection getCollection(MongoClient mongoClient) {
        DB database = mongoClient.getDB(_dbName);
        DBCollection collection = database.getCollection(_collectionName);

        return collection;
    }
}
