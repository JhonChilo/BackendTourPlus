package com.dbp.backendtourplus.location.domain;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.location.dto.LocationDto;
import com.dbp.backendtourplus.location.infrastructure.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    public Location createLocation(LocationDto locationDto) {
        Location location = new Location();
        location.setLatitude(locationDto.getLatitude());
        location.setLongitude(locationDto.getLongitude());
        location.setAddress(locationDto.getAddress());
        return locationRepository.save(location);
    }

    public Location updateLocation(Long id, LocationDto locationDto) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            location.setLatitude(locationDto.getLatitude());
            location.setLongitude(locationDto.getLongitude());
            location.setAddress(locationDto.getAddress());
            return locationRepository.save(location);
        } else {
            throw new ResourceNotFoundException("Location not found with id " + id);
        }
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}
