package com.dbp.backendtourplus.location.application;

import com.dbp.backendtourplus.exceptions.ResourceNotFoundException;
import com.dbp.backendtourplus.location.domain.Location;
import com.dbp.backendtourplus.location.domain.LocationService;
import com.dbp.backendtourplus.location.dto.LocationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Location createLocation(@RequestBody LocationDto locationDto) {
        return locationService.createLocation(locationDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.updateLocation(id, locationDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
