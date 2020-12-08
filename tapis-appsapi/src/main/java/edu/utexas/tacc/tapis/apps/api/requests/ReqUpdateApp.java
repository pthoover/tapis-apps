package edu.utexas.tacc.tapis.apps.api.requests;

import java.util.List;

/*
 * Class representing all app attributes that can be set in an incoming patch request json body
 */
public final class ReqUpdateApp
{
  public String version; // Version of the app
  public String description; // Full description of the app
  public Boolean enabled; // Indicates if apps is currently enabled
//  public List<Capability> jobCapabilities; // List of job related capabilities required by the app
  public String[] tags;       // List of arbitrary tags as strings
  public Object notes;      // Simple metadata as json
}
