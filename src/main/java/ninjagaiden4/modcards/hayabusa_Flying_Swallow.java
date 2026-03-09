package ninjagaiden4.modcards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.modcore.Ninja4;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;


public class hayabusa_Flying_Swallow extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:hayabusa_Flying_Swallow";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Flying_Swallow.png";
    private static final int COST = 0;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public hayabusa_Flying_Swallow() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 18;
        this.tags.add(CardTags.STRIKE);
        this.tags.add(CardTags.STARTER_STRIKE);
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        Ninja4.CardFields.dismemberRate.set(this,0.3F);

        this.exhaust = true; // 卡牌打出后消耗
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(27);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();

            if (this.upgraded) {
                this.exhaust = false;
            }
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        m,
                        new DamageInfo(
                                p,
                                damage,
                                DamageInfo.DamageType.NORMAL
                        )
                )
        );
        addToBot(new core(m,new DamageInfo(p,this.damage,DamageInfo.DamageType.NORMAL),this));
        addToTop(new DrawCardAction(2));
    }

}
