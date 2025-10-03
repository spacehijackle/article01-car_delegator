package delegator.car.power;

import delegator.car.ACMode;
import delegator.car.MotivePower;

/**
 * 電動モーターのクラス
 */
public class ElectricMotor implements MotivePower
{
    /** {@code MotivePower} のコア実装 */
    private final MotivePowerCore delegator;

    /** 初期電費(km/kWh) */
    private double initConsumptionRate;

    public ElectricMotor(double capacity, double fuelEfficiency)
    {
        this.delegator = new MotivePowerCore(capacity, fuelEfficiency);
        this.initConsumptionRate = fuelEfficiency;
    }

    @Override
    public String powerSource() { return "Motor"; }

    @Override
    public double capacity() { return delegator.capacity(); }

    @Override
    public double remains() { return delegator.remains(); }

    @Override
    public void fillUp()
    {
        delegator.fillUp();
    }

    @Override
    public boolean isEmpty()
    {
        return delegator.isEmpty();
    }

    @Override
    public void consumeEvery1km()
    {
        delegator.consumeEvery1km();
    }

    /**
     * バッテリーに充電する。
     * 
     * @apiNote {@code MotivePower#injectEneergyIfPossible()} のエイリアス
     * 
     * @param increment 充電量(kWh)
     * @return true: 充電成功、false: 充電失敗
     */
    public boolean chargeIfPossible(double increment)
    {
        return delegator.injectEnergyIfPossible(increment);
    }

    @Override
    public void onModeChanged(ACMode mode)
    {
		var consumptionRate = switch(mode)
		{
			case OFF     -> initConsumptionRate;
			case HEATING -> initConsumptionRate * 0.8d;  // ヒーターONで20%悪化
		};

        // 電費変更
        delegator.changeConsumptionRate(consumptionRate);
    }
}
