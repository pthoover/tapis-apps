package edu.utexas.tacc.tapis.apps.model;

import edu.utexas.tacc.tapis.apps.model.App.InputMode;
import edu.utexas.tacc.tapis.shared.utils.TapisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/*
 * Class representing FileInputs contained in an App definition.

 *
 * This class is intended to represent an immutable object.
 * Please keep it immutable.
 *
 */
public final class FileInput
{
  /* ********************************************************************** */
  /*                               Constants                                */
  /* ********************************************************************** */

  /* ********************************************************************** */
  /*                                 Fields                                 */
  /* ********************************************************************** */
  // Logging
  private static final Logger _log = LoggerFactory.getLogger(FileInput.class);

  private final String name;
  private final String description;
  private final InputMode inputMode;
  private final boolean autoMountLocal;
  private final String sourceUrl;
  private final String targetPath;

  /* ********************************************************************** */
  /*                           Constructors                                 */
  /* ********************************************************************** */

  public FileInput(String name1, String description1, InputMode inputMode1, boolean autoMountLocal1,
                   String sourceUrl1, String targetPath1)
  {
    name = name1;
    description = description1;
    inputMode = inputMode1;
    autoMountLocal = autoMountLocal1;
    sourceUrl = sourceUrl1;
    targetPath = targetPath1;
  }

  /* ********************************************************************** */
  /*                               Accessors                                */
  /* ********************************************************************** */
  public String getName() { return name; }
  public String getDescription() { return description; }
  public InputMode getInputMode() { return inputMode; }
  public boolean isAutoMountLocal() { return autoMountLocal; }
  public String getSourceUrl() { return sourceUrl; }
  public String getTargetPath() { return targetPath; }

  @Override
  public String toString() {return TapisUtils.toString(this);}
}
