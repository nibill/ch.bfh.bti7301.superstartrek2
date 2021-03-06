package ch.bfh.bti7301.superstartrek.model;

import java.awt.geom.Ellipse2D;

/**
 * Created by filip on 04.11.2016.
 */
public class SpaceStation extends SpaceObject {

    private int fuelCost;
    private int repairCost;

    private int upgradeWeaponMultiplier = 3;
    private int upgradeFuelMultiplier = 2;
    private int upgradeShieldMultiplier = 2;
    private int upgradeHealthMultiplier = 2;

    /**
     * overloaded constructor
     * @param width SpaceStation-width
     * @param height SpaceStation-height
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public SpaceStation(int width, int height, int x, int y){
        super(width, height, x, y);

        this.getSprite("images/PNG/ufoGreen.png");
    }

    /**
     * Sets the Collisionbox to the shape of the image.
     * Polygon has to be generated manually.
     */
    public void setPolygon(){
        shape = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
    }

    public int getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(int fuelCost) {
        this.fuelCost = fuelCost;
    }

    public int getRepairCost() {
        return repairCost;
    }

    public void setRepairCost(int repairCost) {
        this.repairCost = repairCost;
    }

    public boolean canAffordRepair(SpaceShip ship){
        return ((ship.getMoney() >= this.getRepairCostForSpaceShip(ship)));
    }

    public boolean canAffordRefuel(SpaceShip ship){
        return ((ship.getMoney() >= this.getFuelCostForSpaceShip(ship)));
    }

    public boolean canAffordUpgradeWeaponCost(SpaceShip ship, Weapon weapon){
        return ((ship.getMoney() >= this.getUpgradeWeaponCost(weapon)));
    }

    public boolean canAffordUpgradeHealthCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeHealthCost(ship)));
    }

    public boolean canAffordUpgradeShieldCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeShieldCost(ship)));
    }

    public boolean canAffordUpgradeFuelCost(SpaceShip ship){
        return ((ship.getMoney() >= this.getUpgradeFuelCost(ship)));
    }

    public int getFuelCostForSpaceShip(SpaceShip ship){
        return (ship.getMaxFuel() - ship.getFuel()) * this.fuelCost;
    }

    public int getRepairCostForSpaceShip(SpaceShip ship){
        return ship.shipHasDamage() * this.repairCost;
    }

    public int getUpgradeWeaponCost(Weapon weapon){
        return weapon.getDamage() * this.upgradeWeaponMultiplier;
    }

    public int getUpgradeFuelCost(SpaceShip ship){
        return ship.getMaxFuel() * this.upgradeFuelMultiplier;
    }

    public int getUpgradeShieldCost(SpaceShip ship){
        return ship.getHealthMax() * this.upgradeShieldMultiplier;
    }

    public int getUpgradeHealthCost(SpaceShip ship){
        return ship.getHealthMax() * this.upgradeHealthMultiplier;
    }

    /**
     *
     * @param ship SpaceShip Object
     */
    public void refuel(SpaceShip ship){
        if(this.canAffordRefuel(ship)){
            ship.setMoney(ship.getMoney() - this.getFuelCostForSpaceShip(ship));
            ship.setFuel(ship.getMaxFuel());
        }
    }

    /**
     * Repair SpaceShip
     * @param ship SpaceShip Object
     */
    public void repair(SpaceShip ship){
        if(this.canAffordRepair(ship)){
            ship.setMoney(ship.getMoney() - this.getRepairCostForSpaceShip(ship));
            ship.setHealth(ship.getHealthMax());
            ship.setShield(ship.getShieldMax());
        }
    }

    /**
     * Upgrade shield
     * @param ship SpaceShip Object
     */
    public void upgradeShield(SpaceShip ship){
        if(this.canAffordUpgradeShieldCost(ship)){
            ship.setMoney(ship.getMoney() - this.getUpgradeShieldCost(ship));
            ship.setShieldMax(ship.getShieldMax() + 5);
        }
    }

    /**
     * Upgrade fuel
     * @param ship SpaceShip Object
     */
    public void upgradeFuel(SpaceShip  ship){
        if(this.canAffordUpgradeFuelCost(ship)){
            ship.setMoney(ship.getMoney() - this.getUpgradeFuelCost(ship));
            ship.setMaxFuel(ship.getMaxFuel() + 5);
        }
    }

    /**
     * Upgrade weapon
     * @param ship SpaceShip Object
     * @param weapon Weapon Object
     */
    public void upgradeWeapon(SpaceShip ship, Weapon weapon){
        if(this.canAffordUpgradeWeaponCost(ship, weapon)){
            ship.setMoney(ship.getMoney() - this.getUpgradeWeaponCost(weapon));
            weapon.setDamage(weapon.getDamage()+2);
        }
    }

    /**
     * Upgrade health
     * @param ship SpaceShip Object
     */
    public void upgradeHealth(SpaceShip ship){
        if(ship.getMoney() >= ship.getHealthMax()*this.upgradeHealthMultiplier){
            ship.setHealthMax(ship.getHealthMax() + 5);
        }
    }


}
