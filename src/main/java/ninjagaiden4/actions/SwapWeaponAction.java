package ninjagaiden4.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
// 导入我们的接口和智能字典
import ninjagaiden4.helpers.WeaponCard;
import ninjagaiden4.helpers.WeaponRelics;
import ninjagaiden4.relics.Takeminakata;
import ninjagaiden4.relics.Yatousen;

public class SwapWeaponAction extends AbstractGameAction {
    private String targetWeaponRelicId; // 你想要切换到的新武器ID

    public SwapWeaponAction(String targetWeaponRelicId) {
        this.targetWeaponRelicId = targetWeaponRelicId;
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {

            // ==========================================
            // 1. 遗物替换逻辑 (修复了逻辑错误，变得更通用)
            // ==========================================

            // 第一步：不管你现在拿着什么武器，通通卸下
            if (AbstractDungeon.player.hasRelic(Takeminakata.ID)) {
                AbstractDungeon.player.loseRelic(Takeminakata.ID);
            } else if (AbstractDungeon.player.hasRelic("ninjagaiden4:Yatousen")) { // 假设你没在Yatousen里写ID常量，就用字符串
                AbstractDungeon.player.loseRelic("ninjagaiden4:Yatousen");
            }
            // 如果以后有新武器，在这里继续 else if 卸下即可

            // 第二步：根据传入的目标 ID，发给你对应的新武器
            if (targetWeaponRelicId.equals("ninjagaiden4:Yatousen")) {
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Yatousen());
            } else if (targetWeaponRelicId.equals(Takeminakata.ID)) {
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, new Takeminakata());
            }

            // ==========================================
            // 2. 卡牌替换逻辑 (接入全自动图鉴搜索)
            // ==========================================
            replaceCardsInGroup(AbstractDungeon.player.masterDeck);
            replaceCardsInGroup(AbstractDungeon.player.hand);
            replaceCardsInGroup(AbstractDungeon.player.drawPile);
            replaceCardsInGroup(AbstractDungeon.player.discardPile);
            replaceCardsInGroup(AbstractDungeon.player.exhaustPile);

            // 刷新手牌排版，防止手牌显示错位
            AbstractDungeon.player.hand.refreshHandLayout();

            this.isDone = true;
        }
    }

    // 遍历牌堆并替换卡牌的方法
    private void replaceCardsInGroup(CardGroup group) {
        for (int i = 0; i < group.group.size(); i++) {
            AbstractCard oldCard = group.group.get(i);

            // 【魔法时刻】：只有签了 WeaponCard 接口的牌，才允许被替换
            if (oldCard instanceof WeaponCard) {

                // 把新武器的 ID 和要被替换的旧牌，直接扔给我们的智能字典去处理！
                AbstractCard newCard = WeaponRelics.getReplacementCard(targetWeaponRelicId, oldCard);

                // 如果图鉴里找到了对应的平替牌
                if (newCard != null) {
                    // 继承打铁（升级）状态
                    if (oldCard.upgraded) {
                        newCard.upgrade();
                    }
                    // 执行替换
                    group.group.set(i, newCard);
                }
            }
        }
    }

    // 注意：原本写在最下面的那个几十行的 getCorrespondingCard 方法被彻底删除了！
    // 因为我们已经把它外包给 WeaponRelics 去做了。
}