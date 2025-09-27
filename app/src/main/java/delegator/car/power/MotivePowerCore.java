package delegator.car.power;

import delegator.car.ACMode;
import delegator.car.MotivePower;

/**
 * {@link MotivePower} のコア実装クラス
 */
final public class MotivePowerCore implements MotivePower
{
    /** 容量 */
    private final double capacity;

    /** 残量 */
    private double remains;

    /** エネルギー消費率 */
    private double consumptionRate;

    MotivePowerCore(double capacity, double consumptionRate)
    {
        this.capacity = capacity;
        this.consumptionRate = consumptionRate;
    }

    /**
     * @inheritDoc
     * 
     * @apiNote 空文字列を返す。
     */
    @Override
    public String powerSource() { return ""; }

    @Override
    public double capacity() { return capacity; }

    @Override
    public double remains() { return remains; }

    @Override
    public void fillUp()
    {
        this.remains = capacity;
    }

    @Override
    public boolean isEmpty()
    {
        return (remains <= 0);
    }

    @Override
    public void consumeEvery1km()
    {
		remains -= 1.0d / consumptionRate;
        if(remains < 0) remains = 0;
    }

    /**
     * 指定分のエネルギーを注入する。
     * 
     * @param increment 注入量
     * @return true: 注入成功、false: 注入失敗
     */
    boolean injectEnergyIfPossible(double increment)
    {
        if(hasSpace(increment))
        {
            remains += increment;
            return true;
        }

        return false;
    }

    /**
     * 容量に指定分の空きがあるか否かを返す。
     * 
     * @param increment 注入量（正の数）
     * @return true: 空きあり、false: 空きなし
     */
    private boolean hasSpace(double increment)
    {
        if(increment <= 0) return false;

        return (remains + increment < capacity);
    }

    /**
     * 消費率（燃費）を変更する。
     * 
     * @param newRate 消費率（燃費）
     */
    void changeConsumptionRate(double newRate)
    {
        this.consumptionRate = newRate;
    }

    /**
     * @inheritDoc
     * 
     * @apiNote 空実装
     */
    @Override
    public void onModeChanged(ACMode mode) { }
}
