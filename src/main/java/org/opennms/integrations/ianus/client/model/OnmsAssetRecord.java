package org.opennms.integrations.ianus.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * OnmsAssetRecord
 */
public class OnmsAssetRecord {
  @JsonProperty("category")
  private String category = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("port")
  private String port = null;

  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("operatingSystem")
  private String operatingSystem = null;

  @JsonProperty("username")
  private String username = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("assetNumber")
  private String assetNumber = null;

  @JsonProperty("rack")
  private String rack = null;

  @JsonProperty("division")
  private String division = null;

  @JsonProperty("department")
  private String department = null;

  @JsonProperty("building")
  private String building = null;

  @JsonProperty("floor")
  private String floor = null;

  @JsonProperty("room")
  private String room = null;

  @JsonProperty("enable")
  private String enable = null;

  @JsonProperty("autoenable")
  private String autoenable = null;

  @JsonProperty("vendorPhone")
  private String vendorPhone = null;

  @JsonProperty("vendorFax")
  private String vendorFax = null;

  @JsonProperty("vendorAssetNumber")
  private String vendorAssetNumber = null;

  @JsonProperty("lastModifiedBy")
  private String lastModifiedBy = null;

  @JsonProperty("lastModifiedDate")
  private OffsetDateTime lastModifiedDate = null;

  @JsonProperty("dateInstalled")
  private String dateInstalled = null;

  @JsonProperty("lease")
  private String lease = null;

  @JsonProperty("leaseExpires")
  private String leaseExpires = null;

  @JsonProperty("supportPhone")
  private String supportPhone = null;

  @JsonProperty("maintContractExpiration")
  private String maintContractExpiration = null;

  @JsonProperty("displayCategory")
  private String displayCategory = null;

  @JsonProperty("notifyCategory")
  private String notifyCategory = null;

  @JsonProperty("pollerCategory")
  private String pollerCategory = null;

  @JsonProperty("thresholdCategory")
  private String thresholdCategory = null;

  @JsonProperty("cpu")
  private String cpu = null;

  @JsonProperty("ram")
  private String ram = null;

  @JsonProperty("storagectrl")
  private String storagectrl = null;

  @JsonProperty("hdd1")
  private String hdd1 = null;

  @JsonProperty("hdd2")
  private String hdd2 = null;

  @JsonProperty("hdd3")
  private String hdd3 = null;

  @JsonProperty("hdd4")
  private String hdd4 = null;

  @JsonProperty("hdd5")
  private String hdd5 = null;

  @JsonProperty("hdd6")
  private String hdd6 = null;

  @JsonProperty("numpowersupplies")
  private String numpowersupplies = null;

  @JsonProperty("inputpower")
  private String inputpower = null;

  @JsonProperty("additionalhardware")
  private String additionalhardware = null;

  @JsonProperty("admin")
  private String admin = null;

  @JsonProperty("snmpcommunity")
  private String snmpcommunity = null;

  @JsonProperty("rackunitheight")
  private String rackunitheight = null;

  @JsonProperty("geolocation")
  private OnmsGeolocation geolocation = null;

  @JsonProperty("maintcontract")
  private String maintcontract = null;

  @JsonProperty("address1")
  private String address1 = null;

  @JsonProperty("address2")
  private String address2 = null;

  @JsonProperty("city")
  private String city = null;

  @JsonProperty("zip")
  private String zip = null;

  @JsonProperty("country")
  private String country = null;

  @JsonProperty("longitude")
  private Double longitude = null;

  @JsonProperty("latitude")
  private Double latitude = null;

  @JsonProperty("manufacturer")
  private String manufacturer = null;

  @JsonProperty("vendor")
  private String vendor = null;

  @JsonProperty("modelNumber")
  private String modelNumber = null;

  @JsonProperty("serialNumber")
  private String serialNumber = null;

  @JsonProperty("circuitId")
  private String circuitId = null;

  @JsonProperty("slot")
  private String slot = null;

  @JsonProperty("maintContractNumber")
  private String maintContractNumber = null;

  @JsonProperty("managedObjectType")
  private String managedObjectType = null;

  @JsonProperty("managedObjectInstance")
  private String managedObjectInstance = null;

  @JsonProperty("connection")
  private String connection = null;

  @JsonProperty("state")
  private String state = null;

  @JsonProperty("node")
  private OnmsNode node = null;

  @JsonProperty("region")
  private String region = null;

  @JsonProperty("comment")
  private String comment = null;

  public OnmsAssetRecord category(String category) {
    this.category = category;
    return this;
  }

   /**
   * Get category
   * @return category
  **/
 
  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public OnmsAssetRecord password(String password) {
    this.password = password;
    return this;
  }

   /**
   * Get password
   * @return password
  **/
 
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public OnmsAssetRecord port(String port) {
    this.port = port;
    return this;
  }

   /**
   * Get port
   * @return port
  **/
 
  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public OnmsAssetRecord id(Integer id) {
    this.id = id;
    return this;
  }

   /**
   * Get id
   * @return id
  **/
 
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OnmsAssetRecord operatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
    return this;
  }

   /**
   * Get operatingSystem
   * @return operatingSystem
  **/
 
  public String getOperatingSystem() {
    return operatingSystem;
  }

  public void setOperatingSystem(String operatingSystem) {
    this.operatingSystem = operatingSystem;
  }

  public OnmsAssetRecord username(String username) {
    this.username = username;
    return this;
  }

   /**
   * Get username
   * @return username
  **/
 
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public OnmsAssetRecord description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
 
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OnmsAssetRecord assetNumber(String assetNumber) {
    this.assetNumber = assetNumber;
    return this;
  }

   /**
   * Get assetNumber
   * @return assetNumber
  **/
 
  public String getAssetNumber() {
    return assetNumber;
  }

  public void setAssetNumber(String assetNumber) {
    this.assetNumber = assetNumber;
  }

  public OnmsAssetRecord rack(String rack) {
    this.rack = rack;
    return this;
  }

   /**
   * Get rack
   * @return rack
  **/
 
  public String getRack() {
    return rack;
  }

  public void setRack(String rack) {
    this.rack = rack;
  }

  public OnmsAssetRecord division(String division) {
    this.division = division;
    return this;
  }

   /**
   * Get division
   * @return division
  **/
 
  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public OnmsAssetRecord department(String department) {
    this.department = department;
    return this;
  }

   /**
   * Get department
   * @return department
  **/
 
  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public OnmsAssetRecord building(String building) {
    this.building = building;
    return this;
  }

   /**
   * Get building
   * @return building
  **/
 
  public String getBuilding() {
    return building;
  }

  public void setBuilding(String building) {
    this.building = building;
  }

  public OnmsAssetRecord floor(String floor) {
    this.floor = floor;
    return this;
  }

   /**
   * Get floor
   * @return floor
  **/
 
  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public OnmsAssetRecord room(String room) {
    this.room = room;
    return this;
  }

   /**
   * Get room
   * @return room
  **/
 
  public String getRoom() {
    return room;
  }

  public void setRoom(String room) {
    this.room = room;
  }

  public OnmsAssetRecord enable(String enable) {
    this.enable = enable;
    return this;
  }

   /**
   * Get enable
   * @return enable
  **/
 
  public String getEnable() {
    return enable;
  }

  public void setEnable(String enable) {
    this.enable = enable;
  }

  public OnmsAssetRecord autoenable(String autoenable) {
    this.autoenable = autoenable;
    return this;
  }

   /**
   * Get autoenable
   * @return autoenable
  **/
 
  public String getAutoenable() {
    return autoenable;
  }

  public void setAutoenable(String autoenable) {
    this.autoenable = autoenable;
  }

  public OnmsAssetRecord vendorPhone(String vendorPhone) {
    this.vendorPhone = vendorPhone;
    return this;
  }

   /**
   * Get vendorPhone
   * @return vendorPhone
  **/
 
  public String getVendorPhone() {
    return vendorPhone;
  }

  public void setVendorPhone(String vendorPhone) {
    this.vendorPhone = vendorPhone;
  }

  public OnmsAssetRecord vendorFax(String vendorFax) {
    this.vendorFax = vendorFax;
    return this;
  }

   /**
   * Get vendorFax
   * @return vendorFax
  **/
 
  public String getVendorFax() {
    return vendorFax;
  }

  public void setVendorFax(String vendorFax) {
    this.vendorFax = vendorFax;
  }

  public OnmsAssetRecord vendorAssetNumber(String vendorAssetNumber) {
    this.vendorAssetNumber = vendorAssetNumber;
    return this;
  }

   /**
   * Get vendorAssetNumber
   * @return vendorAssetNumber
  **/
 
  public String getVendorAssetNumber() {
    return vendorAssetNumber;
  }

  public void setVendorAssetNumber(String vendorAssetNumber) {
    this.vendorAssetNumber = vendorAssetNumber;
  }

  public OnmsAssetRecord lastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
    return this;
  }

   /**
   * Get lastModifiedBy
   * @return lastModifiedBy
  **/
 
  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public OnmsAssetRecord lastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
    return this;
  }

   /**
   * Get lastModifiedDate
   * @return lastModifiedDate
  **/
 
  public OffsetDateTime getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(OffsetDateTime lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public OnmsAssetRecord dateInstalled(String dateInstalled) {
    this.dateInstalled = dateInstalled;
    return this;
  }

   /**
   * Get dateInstalled
   * @return dateInstalled
  **/
 
  public String getDateInstalled() {
    return dateInstalled;
  }

  public void setDateInstalled(String dateInstalled) {
    this.dateInstalled = dateInstalled;
  }

  public OnmsAssetRecord lease(String lease) {
    this.lease = lease;
    return this;
  }

   /**
   * Get lease
   * @return lease
  **/
 
  public String getLease() {
    return lease;
  }

  public void setLease(String lease) {
    this.lease = lease;
  }

  public OnmsAssetRecord leaseExpires(String leaseExpires) {
    this.leaseExpires = leaseExpires;
    return this;
  }

   /**
   * Get leaseExpires
   * @return leaseExpires
  **/
 
  public String getLeaseExpires() {
    return leaseExpires;
  }

  public void setLeaseExpires(String leaseExpires) {
    this.leaseExpires = leaseExpires;
  }

  public OnmsAssetRecord supportPhone(String supportPhone) {
    this.supportPhone = supportPhone;
    return this;
  }

   /**
   * Get supportPhone
   * @return supportPhone
  **/
 
  public String getSupportPhone() {
    return supportPhone;
  }

  public void setSupportPhone(String supportPhone) {
    this.supportPhone = supportPhone;
  }

  public OnmsAssetRecord maintContractExpiration(String maintContractExpiration) {
    this.maintContractExpiration = maintContractExpiration;
    return this;
  }

   /**
   * Get maintContractExpiration
   * @return maintContractExpiration
  **/
 
  public String getMaintContractExpiration() {
    return maintContractExpiration;
  }

  public void setMaintContractExpiration(String maintContractExpiration) {
    this.maintContractExpiration = maintContractExpiration;
  }

  public OnmsAssetRecord displayCategory(String displayCategory) {
    this.displayCategory = displayCategory;
    return this;
  }

   /**
   * Get displayCategory
   * @return displayCategory
  **/
 
  public String getDisplayCategory() {
    return displayCategory;
  }

  public void setDisplayCategory(String displayCategory) {
    this.displayCategory = displayCategory;
  }

  public OnmsAssetRecord notifyCategory(String notifyCategory) {
    this.notifyCategory = notifyCategory;
    return this;
  }

   /**
   * Get notifyCategory
   * @return notifyCategory
  **/
 
  public String getNotifyCategory() {
    return notifyCategory;
  }

  public void setNotifyCategory(String notifyCategory) {
    this.notifyCategory = notifyCategory;
  }

  public OnmsAssetRecord pollerCategory(String pollerCategory) {
    this.pollerCategory = pollerCategory;
    return this;
  }

   /**
   * Get pollerCategory
   * @return pollerCategory
  **/
 
  public String getPollerCategory() {
    return pollerCategory;
  }

  public void setPollerCategory(String pollerCategory) {
    this.pollerCategory = pollerCategory;
  }

  public OnmsAssetRecord thresholdCategory(String thresholdCategory) {
    this.thresholdCategory = thresholdCategory;
    return this;
  }

   /**
   * Get thresholdCategory
   * @return thresholdCategory
  **/
 
  public String getThresholdCategory() {
    return thresholdCategory;
  }

  public void setThresholdCategory(String thresholdCategory) {
    this.thresholdCategory = thresholdCategory;
  }

  public OnmsAssetRecord cpu(String cpu) {
    this.cpu = cpu;
    return this;
  }

   /**
   * Get cpu
   * @return cpu
  **/
 
  public String getCpu() {
    return cpu;
  }

  public void setCpu(String cpu) {
    this.cpu = cpu;
  }

  public OnmsAssetRecord ram(String ram) {
    this.ram = ram;
    return this;
  }

   /**
   * Get ram
   * @return ram
  **/
 
  public String getRam() {
    return ram;
  }

  public void setRam(String ram) {
    this.ram = ram;
  }

  public OnmsAssetRecord storagectrl(String storagectrl) {
    this.storagectrl = storagectrl;
    return this;
  }

   /**
   * Get storagectrl
   * @return storagectrl
  **/
 
  public String getStoragectrl() {
    return storagectrl;
  }

  public void setStoragectrl(String storagectrl) {
    this.storagectrl = storagectrl;
  }

  public OnmsAssetRecord hdd1(String hdd1) {
    this.hdd1 = hdd1;
    return this;
  }

   /**
   * Get hdd1
   * @return hdd1
  **/
 
  public String getHdd1() {
    return hdd1;
  }

  public void setHdd1(String hdd1) {
    this.hdd1 = hdd1;
  }

  public OnmsAssetRecord hdd2(String hdd2) {
    this.hdd2 = hdd2;
    return this;
  }

   /**
   * Get hdd2
   * @return hdd2
  **/
 
  public String getHdd2() {
    return hdd2;
  }

  public void setHdd2(String hdd2) {
    this.hdd2 = hdd2;
  }

  public OnmsAssetRecord hdd3(String hdd3) {
    this.hdd3 = hdd3;
    return this;
  }

   /**
   * Get hdd3
   * @return hdd3
  **/
 
  public String getHdd3() {
    return hdd3;
  }

  public void setHdd3(String hdd3) {
    this.hdd3 = hdd3;
  }

  public OnmsAssetRecord hdd4(String hdd4) {
    this.hdd4 = hdd4;
    return this;
  }

   /**
   * Get hdd4
   * @return hdd4
  **/
 
  public String getHdd4() {
    return hdd4;
  }

  public void setHdd4(String hdd4) {
    this.hdd4 = hdd4;
  }

  public OnmsAssetRecord hdd5(String hdd5) {
    this.hdd5 = hdd5;
    return this;
  }

   /**
   * Get hdd5
   * @return hdd5
  **/
 
  public String getHdd5() {
    return hdd5;
  }

  public void setHdd5(String hdd5) {
    this.hdd5 = hdd5;
  }

  public OnmsAssetRecord hdd6(String hdd6) {
    this.hdd6 = hdd6;
    return this;
  }

   /**
   * Get hdd6
   * @return hdd6
  **/
 
  public String getHdd6() {
    return hdd6;
  }

  public void setHdd6(String hdd6) {
    this.hdd6 = hdd6;
  }

  public OnmsAssetRecord numpowersupplies(String numpowersupplies) {
    this.numpowersupplies = numpowersupplies;
    return this;
  }

   /**
   * Get numpowersupplies
   * @return numpowersupplies
  **/
 
  public String getNumpowersupplies() {
    return numpowersupplies;
  }

  public void setNumpowersupplies(String numpowersupplies) {
    this.numpowersupplies = numpowersupplies;
  }

  public OnmsAssetRecord inputpower(String inputpower) {
    this.inputpower = inputpower;
    return this;
  }

   /**
   * Get inputpower
   * @return inputpower
  **/
 
  public String getInputpower() {
    return inputpower;
  }

  public void setInputpower(String inputpower) {
    this.inputpower = inputpower;
  }

  public OnmsAssetRecord additionalhardware(String additionalhardware) {
    this.additionalhardware = additionalhardware;
    return this;
  }

   /**
   * Get additionalhardware
   * @return additionalhardware
  **/
 
  public String getAdditionalhardware() {
    return additionalhardware;
  }

  public void setAdditionalhardware(String additionalhardware) {
    this.additionalhardware = additionalhardware;
  }

  public OnmsAssetRecord admin(String admin) {
    this.admin = admin;
    return this;
  }

   /**
   * Get admin
   * @return admin
  **/
 
  public String getAdmin() {
    return admin;
  }

  public void setAdmin(String admin) {
    this.admin = admin;
  }

  public OnmsAssetRecord snmpcommunity(String snmpcommunity) {
    this.snmpcommunity = snmpcommunity;
    return this;
  }

   /**
   * Get snmpcommunity
   * @return snmpcommunity
  **/
 
  public String getSnmpcommunity() {
    return snmpcommunity;
  }

  public void setSnmpcommunity(String snmpcommunity) {
    this.snmpcommunity = snmpcommunity;
  }

  public OnmsAssetRecord rackunitheight(String rackunitheight) {
    this.rackunitheight = rackunitheight;
    return this;
  }

   /**
   * Get rackunitheight
   * @return rackunitheight
  **/
 
  public String getRackunitheight() {
    return rackunitheight;
  }

  public void setRackunitheight(String rackunitheight) {
    this.rackunitheight = rackunitheight;
  }

  public OnmsAssetRecord geolocation(OnmsGeolocation geolocation) {
    this.geolocation = geolocation;
    return this;
  }

   /**
   * Get geolocation
   * @return geolocation
  **/
 
  public OnmsGeolocation getGeolocation() {
    return geolocation;
  }

  public void setGeolocation(OnmsGeolocation geolocation) {
    this.geolocation = geolocation;
  }

  public OnmsAssetRecord maintcontract(String maintcontract) {
    this.maintcontract = maintcontract;
    return this;
  }

   /**
   * Get maintcontract
   * @return maintcontract
  **/
 
  public String getMaintcontract() {
    return maintcontract;
  }

  public void setMaintcontract(String maintcontract) {
    this.maintcontract = maintcontract;
  }

  public OnmsAssetRecord address1(String address1) {
    this.address1 = address1;
    return this;
  }

   /**
   * Get address1
   * @return address1
  **/
 
  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public OnmsAssetRecord address2(String address2) {
    this.address2 = address2;
    return this;
  }

   /**
   * Get address2
   * @return address2
  **/
 
  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public OnmsAssetRecord city(String city) {
    this.city = city;
    return this;
  }

   /**
   * Get city
   * @return city
  **/
 
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public OnmsAssetRecord zip(String zip) {
    this.zip = zip;
    return this;
  }

   /**
   * Get zip
   * @return zip
  **/
 
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public OnmsAssetRecord country(String country) {
    this.country = country;
    return this;
  }

   /**
   * Get country
   * @return country
  **/
 
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public OnmsAssetRecord longitude(Double longitude) {
    this.longitude = longitude;
    return this;
  }

   /**
   * Get longitude
   * @return longitude
  **/
 
  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public OnmsAssetRecord latitude(Double latitude) {
    this.latitude = latitude;
    return this;
  }

   /**
   * Get latitude
   * @return latitude
  **/
 
  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public OnmsAssetRecord manufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
    return this;
  }

   /**
   * Get manufacturer
   * @return manufacturer
  **/
 
  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public OnmsAssetRecord vendor(String vendor) {
    this.vendor = vendor;
    return this;
  }

   /**
   * Get vendor
   * @return vendor
  **/
 
  public String getVendor() {
    return vendor;
  }

  public void setVendor(String vendor) {
    this.vendor = vendor;
  }

  public OnmsAssetRecord modelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
    return this;
  }

   /**
   * Get modelNumber
   * @return modelNumber
  **/
 
  public String getModelNumber() {
    return modelNumber;
  }

  public void setModelNumber(String modelNumber) {
    this.modelNumber = modelNumber;
  }

  public OnmsAssetRecord serialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
    return this;
  }

   /**
   * Get serialNumber
   * @return serialNumber
  **/
 
  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public OnmsAssetRecord circuitId(String circuitId) {
    this.circuitId = circuitId;
    return this;
  }

   /**
   * Get circuitId
   * @return circuitId
  **/
 
  public String getCircuitId() {
    return circuitId;
  }

  public void setCircuitId(String circuitId) {
    this.circuitId = circuitId;
  }

  public OnmsAssetRecord slot(String slot) {
    this.slot = slot;
    return this;
  }

   /**
   * Get slot
   * @return slot
  **/
 
  public String getSlot() {
    return slot;
  }

  public void setSlot(String slot) {
    this.slot = slot;
  }

  public OnmsAssetRecord maintContractNumber(String maintContractNumber) {
    this.maintContractNumber = maintContractNumber;
    return this;
  }

   /**
   * Get maintContractNumber
   * @return maintContractNumber
  **/
 
  public String getMaintContractNumber() {
    return maintContractNumber;
  }

  public void setMaintContractNumber(String maintContractNumber) {
    this.maintContractNumber = maintContractNumber;
  }

  public OnmsAssetRecord managedObjectType(String managedObjectType) {
    this.managedObjectType = managedObjectType;
    return this;
  }

   /**
   * Get managedObjectType
   * @return managedObjectType
  **/
 
  public String getManagedObjectType() {
    return managedObjectType;
  }

  public void setManagedObjectType(String managedObjectType) {
    this.managedObjectType = managedObjectType;
  }

  public OnmsAssetRecord managedObjectInstance(String managedObjectInstance) {
    this.managedObjectInstance = managedObjectInstance;
    return this;
  }

   /**
   * Get managedObjectInstance
   * @return managedObjectInstance
  **/
 
  public String getManagedObjectInstance() {
    return managedObjectInstance;
  }

  public void setManagedObjectInstance(String managedObjectInstance) {
    this.managedObjectInstance = managedObjectInstance;
  }

  public OnmsAssetRecord connection(String connection) {
    this.connection = connection;
    return this;
  }

   /**
   * Get connection
   * @return connection
  **/
 
  public String getConnection() {
    return connection;
  }

  public void setConnection(String connection) {
    this.connection = connection;
  }

  public OnmsAssetRecord state(String state) {
    this.state = state;
    return this;
  }

   /**
   * Get state
   * @return state
  **/
 
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public OnmsAssetRecord node(OnmsNode node) {
    this.node = node;
    return this;
  }

   /**
   * Get node
   * @return node
  **/
 
  public OnmsNode getNode() {
    return node;
  }

  public void setNode(OnmsNode node) {
    this.node = node;
  }

  public OnmsAssetRecord region(String region) {
    this.region = region;
    return this;
  }

   /**
   * Get region
   * @return region
  **/
 
  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public OnmsAssetRecord comment(String comment) {
    this.comment = comment;
    return this;
  }

   /**
   * Get comment
   * @return comment
  **/
 
  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OnmsAssetRecord onmsAssetRecord = (OnmsAssetRecord) o;
    return Objects.equals(this.category, onmsAssetRecord.category) &&
        Objects.equals(this.password, onmsAssetRecord.password) &&
        Objects.equals(this.port, onmsAssetRecord.port) &&
        Objects.equals(this.id, onmsAssetRecord.id) &&
        Objects.equals(this.operatingSystem, onmsAssetRecord.operatingSystem) &&
        Objects.equals(this.username, onmsAssetRecord.username) &&
        Objects.equals(this.description, onmsAssetRecord.description) &&
        Objects.equals(this.assetNumber, onmsAssetRecord.assetNumber) &&
        Objects.equals(this.rack, onmsAssetRecord.rack) &&
        Objects.equals(this.division, onmsAssetRecord.division) &&
        Objects.equals(this.department, onmsAssetRecord.department) &&
        Objects.equals(this.building, onmsAssetRecord.building) &&
        Objects.equals(this.floor, onmsAssetRecord.floor) &&
        Objects.equals(this.room, onmsAssetRecord.room) &&
        Objects.equals(this.enable, onmsAssetRecord.enable) &&
        Objects.equals(this.autoenable, onmsAssetRecord.autoenable) &&
        Objects.equals(this.vendorPhone, onmsAssetRecord.vendorPhone) &&
        Objects.equals(this.vendorFax, onmsAssetRecord.vendorFax) &&
        Objects.equals(this.vendorAssetNumber, onmsAssetRecord.vendorAssetNumber) &&
        Objects.equals(this.lastModifiedBy, onmsAssetRecord.lastModifiedBy) &&
        Objects.equals(this.lastModifiedDate, onmsAssetRecord.lastModifiedDate) &&
        Objects.equals(this.dateInstalled, onmsAssetRecord.dateInstalled) &&
        Objects.equals(this.lease, onmsAssetRecord.lease) &&
        Objects.equals(this.leaseExpires, onmsAssetRecord.leaseExpires) &&
        Objects.equals(this.supportPhone, onmsAssetRecord.supportPhone) &&
        Objects.equals(this.maintContractExpiration, onmsAssetRecord.maintContractExpiration) &&
        Objects.equals(this.displayCategory, onmsAssetRecord.displayCategory) &&
        Objects.equals(this.notifyCategory, onmsAssetRecord.notifyCategory) &&
        Objects.equals(this.pollerCategory, onmsAssetRecord.pollerCategory) &&
        Objects.equals(this.thresholdCategory, onmsAssetRecord.thresholdCategory) &&
        Objects.equals(this.cpu, onmsAssetRecord.cpu) &&
        Objects.equals(this.ram, onmsAssetRecord.ram) &&
        Objects.equals(this.storagectrl, onmsAssetRecord.storagectrl) &&
        Objects.equals(this.hdd1, onmsAssetRecord.hdd1) &&
        Objects.equals(this.hdd2, onmsAssetRecord.hdd2) &&
        Objects.equals(this.hdd3, onmsAssetRecord.hdd3) &&
        Objects.equals(this.hdd4, onmsAssetRecord.hdd4) &&
        Objects.equals(this.hdd5, onmsAssetRecord.hdd5) &&
        Objects.equals(this.hdd6, onmsAssetRecord.hdd6) &&
        Objects.equals(this.numpowersupplies, onmsAssetRecord.numpowersupplies) &&
        Objects.equals(this.inputpower, onmsAssetRecord.inputpower) &&
        Objects.equals(this.additionalhardware, onmsAssetRecord.additionalhardware) &&
        Objects.equals(this.admin, onmsAssetRecord.admin) &&
        Objects.equals(this.snmpcommunity, onmsAssetRecord.snmpcommunity) &&
        Objects.equals(this.rackunitheight, onmsAssetRecord.rackunitheight) &&
        Objects.equals(this.geolocation, onmsAssetRecord.geolocation) &&
        Objects.equals(this.maintcontract, onmsAssetRecord.maintcontract) &&
        Objects.equals(this.address1, onmsAssetRecord.address1) &&
        Objects.equals(this.address2, onmsAssetRecord.address2) &&
        Objects.equals(this.city, onmsAssetRecord.city) &&
        Objects.equals(this.zip, onmsAssetRecord.zip) &&
        Objects.equals(this.country, onmsAssetRecord.country) &&
        Objects.equals(this.longitude, onmsAssetRecord.longitude) &&
        Objects.equals(this.latitude, onmsAssetRecord.latitude) &&
        Objects.equals(this.manufacturer, onmsAssetRecord.manufacturer) &&
        Objects.equals(this.vendor, onmsAssetRecord.vendor) &&
        Objects.equals(this.modelNumber, onmsAssetRecord.modelNumber) &&
        Objects.equals(this.serialNumber, onmsAssetRecord.serialNumber) &&
        Objects.equals(this.circuitId, onmsAssetRecord.circuitId) &&
        Objects.equals(this.slot, onmsAssetRecord.slot) &&
        Objects.equals(this.maintContractNumber, onmsAssetRecord.maintContractNumber) &&
        Objects.equals(this.managedObjectType, onmsAssetRecord.managedObjectType) &&
        Objects.equals(this.managedObjectInstance, onmsAssetRecord.managedObjectInstance) &&
        Objects.equals(this.connection, onmsAssetRecord.connection) &&
        Objects.equals(this.state, onmsAssetRecord.state) &&
        Objects.equals(this.node, onmsAssetRecord.node) &&
        Objects.equals(this.region, onmsAssetRecord.region) &&
        Objects.equals(this.comment, onmsAssetRecord.comment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(category, password, port, id, operatingSystem, username, description, assetNumber, rack, division, department, building, floor, room, enable, autoenable, vendorPhone, vendorFax, vendorAssetNumber, lastModifiedBy, lastModifiedDate, dateInstalled, lease, leaseExpires, supportPhone, maintContractExpiration, displayCategory, notifyCategory, pollerCategory, thresholdCategory, cpu, ram, storagectrl, hdd1, hdd2, hdd3, hdd4, hdd5, hdd6, numpowersupplies, inputpower, additionalhardware, admin, snmpcommunity, rackunitheight, geolocation, maintcontract, address1, address2, city, zip, country, longitude, latitude, manufacturer, vendor, modelNumber, serialNumber, circuitId, slot, maintContractNumber, managedObjectType, managedObjectInstance, connection, state, node, region, comment);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OnmsAssetRecord {\n");
    
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    port: ").append(toIndentedString(port)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    operatingSystem: ").append(toIndentedString(operatingSystem)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    assetNumber: ").append(toIndentedString(assetNumber)).append("\n");
    sb.append("    rack: ").append(toIndentedString(rack)).append("\n");
    sb.append("    division: ").append(toIndentedString(division)).append("\n");
    sb.append("    department: ").append(toIndentedString(department)).append("\n");
    sb.append("    building: ").append(toIndentedString(building)).append("\n");
    sb.append("    floor: ").append(toIndentedString(floor)).append("\n");
    sb.append("    room: ").append(toIndentedString(room)).append("\n");
    sb.append("    enable: ").append(toIndentedString(enable)).append("\n");
    sb.append("    autoenable: ").append(toIndentedString(autoenable)).append("\n");
    sb.append("    vendorPhone: ").append(toIndentedString(vendorPhone)).append("\n");
    sb.append("    vendorFax: ").append(toIndentedString(vendorFax)).append("\n");
    sb.append("    vendorAssetNumber: ").append(toIndentedString(vendorAssetNumber)).append("\n");
    sb.append("    lastModifiedBy: ").append(toIndentedString(lastModifiedBy)).append("\n");
    sb.append("    lastModifiedDate: ").append(toIndentedString(lastModifiedDate)).append("\n");
    sb.append("    dateInstalled: ").append(toIndentedString(dateInstalled)).append("\n");
    sb.append("    lease: ").append(toIndentedString(lease)).append("\n");
    sb.append("    leaseExpires: ").append(toIndentedString(leaseExpires)).append("\n");
    sb.append("    supportPhone: ").append(toIndentedString(supportPhone)).append("\n");
    sb.append("    maintContractExpiration: ").append(toIndentedString(maintContractExpiration)).append("\n");
    sb.append("    displayCategory: ").append(toIndentedString(displayCategory)).append("\n");
    sb.append("    notifyCategory: ").append(toIndentedString(notifyCategory)).append("\n");
    sb.append("    pollerCategory: ").append(toIndentedString(pollerCategory)).append("\n");
    sb.append("    thresholdCategory: ").append(toIndentedString(thresholdCategory)).append("\n");
    sb.append("    cpu: ").append(toIndentedString(cpu)).append("\n");
    sb.append("    ram: ").append(toIndentedString(ram)).append("\n");
    sb.append("    storagectrl: ").append(toIndentedString(storagectrl)).append("\n");
    sb.append("    hdd1: ").append(toIndentedString(hdd1)).append("\n");
    sb.append("    hdd2: ").append(toIndentedString(hdd2)).append("\n");
    sb.append("    hdd3: ").append(toIndentedString(hdd3)).append("\n");
    sb.append("    hdd4: ").append(toIndentedString(hdd4)).append("\n");
    sb.append("    hdd5: ").append(toIndentedString(hdd5)).append("\n");
    sb.append("    hdd6: ").append(toIndentedString(hdd6)).append("\n");
    sb.append("    numpowersupplies: ").append(toIndentedString(numpowersupplies)).append("\n");
    sb.append("    inputpower: ").append(toIndentedString(inputpower)).append("\n");
    sb.append("    additionalhardware: ").append(toIndentedString(additionalhardware)).append("\n");
    sb.append("    admin: ").append(toIndentedString(admin)).append("\n");
    sb.append("    snmpcommunity: ").append(toIndentedString(snmpcommunity)).append("\n");
    sb.append("    rackunitheight: ").append(toIndentedString(rackunitheight)).append("\n");
    sb.append("    geolocation: ").append(toIndentedString(geolocation)).append("\n");
    sb.append("    maintcontract: ").append(toIndentedString(maintcontract)).append("\n");
    sb.append("    address1: ").append(toIndentedString(address1)).append("\n");
    sb.append("    address2: ").append(toIndentedString(address2)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    longitude: ").append(toIndentedString(longitude)).append("\n");
    sb.append("    latitude: ").append(toIndentedString(latitude)).append("\n");
    sb.append("    manufacturer: ").append(toIndentedString(manufacturer)).append("\n");
    sb.append("    vendor: ").append(toIndentedString(vendor)).append("\n");
    sb.append("    modelNumber: ").append(toIndentedString(modelNumber)).append("\n");
    sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
    sb.append("    circuitId: ").append(toIndentedString(circuitId)).append("\n");
    sb.append("    slot: ").append(toIndentedString(slot)).append("\n");
    sb.append("    maintContractNumber: ").append(toIndentedString(maintContractNumber)).append("\n");
    sb.append("    managedObjectType: ").append(toIndentedString(managedObjectType)).append("\n");
    sb.append("    managedObjectInstance: ").append(toIndentedString(managedObjectInstance)).append("\n");
    sb.append("    connection: ").append(toIndentedString(connection)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    node: ").append(toIndentedString(node)).append("\n");
    sb.append("    region: ").append(toIndentedString(region)).append("\n");
    sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}
