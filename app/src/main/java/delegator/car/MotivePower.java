package delegator.car;

/**
 * 動力源のインターフェース定義
 */
public interface MotivePower extends Cloneable
{
    /** 動力源名を返す。*/
    String powerSource();

    /** 容量を返す。*/
    double capacity();

    /** 残量を返す。*/
    double remains();

    /** 満タンにする。*/
    void fillUp();

    /** 空かどうか返す。*/
    boolean isEmpty();

    /** 1km走行した際のエネルギーを消費する。*/
	void consumeEvery1km();

    /** モード変更イベント処理 */
    void onModeChanged(ACMode mode);

    /** クローンを返す。*/
    MotivePower clone();
}
