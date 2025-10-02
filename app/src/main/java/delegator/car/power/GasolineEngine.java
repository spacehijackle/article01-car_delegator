package delegator.car.power;

import delegator.car.ACMode;
import delegator.car.MotivePower;

/**
 * ガソリンエンジンのクラス
 */
public class GasolineEngine implements MotivePower
{
    /** {@code MotivePower} のコア実装 */
    private final MotivePowerCore delegator;

    public GasolineEngine(double capacity, double fuelEfficiency)
    {
        this.delegator = new MotivePowerCore(capacity, fuelEfficiency);
    }

    @Override
    public String powerSource() { return "Gas Engine"; }

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

    @Override
    public void onModeChanged(ACMode mode)
    {
        // モードの変更で燃費は不変
        // ※ヒーターONでもエンジンの排熱を利用するので燃費は変わらない。
    }
}
