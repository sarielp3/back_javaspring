package mx.com.basantader.AgenciaViajeTD.service;

import java.util.List;

import mx.com.basantader.AgenciaViajeTD.dto.ApplicationEntry;

public interface ApplicationService {
	
	public void createApplicationItem(ApplicationEntry request);

    public List<ApplicationEntry> getApplicationItems();

}
