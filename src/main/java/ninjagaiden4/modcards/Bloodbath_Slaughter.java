package ninjagaiden4.modcards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import ninjagaiden4.actions.core;
import ninjagaiden4.helpers.ModHelper;
import ninjagaiden4.modcore.Ninja4;
import ninjagaiden4.relics.hitogoroshi;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;

public class Bloodbath_Slaughter extends CustomCard implements ModHelper {
    public static final String ID = "ninjagaiden4:Bloodbath_Slaughter";
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String NAME = CARD_STRINGS.NAME;
    private static final String IMG_PATH = "lingqiu1700/ninjagaiden4/images/cards/attack/Bloodbath_Slaughter.png";
    private static final int COST = 20;
    private static final String DESCRIPTION = CARD_STRINGS.DESCRIPTION;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardColor COLOR = YAKUMO_CARD_COLOR;
    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    public Bloodbath_Slaughter() {
        super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = 200;
        this.tags.add(ninjagaiden4.modcore.Ninja4.CURSED_BLADES);
        Ninja4.CardFields.dismemberRate.set(this,1.0F);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasRelic(hitogoroshi.ID)) {
            hitogoroshi relic = (hitogoroshi) AbstractDungeon.player.getRelic(hitogoroshi.ID);

            if (relic.bloodhasCharge()){
                this.freeToPlayOnce = true;
            }
            else  {
                this.freeToPlayOnce = false;
            }
        }
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.player.hasRelic(hitogoroshi.ID)) {
            hitogoroshi relic = (hitogoroshi) AbstractDungeon.player.getRelic(hitogoroshi.ID);
            if (relic.bloodhasCharge()) {
                this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
            }
        }
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(200);

            this.rawDescription = CARD_STRINGS.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        if (p.hasRelic(hitogoroshi.ID)) {
            hitogoroshi relic = (hitogoroshi) AbstractDungeon.player.getRelic(hitogoroshi.ID);
            if (relic.bloodhasCharge()) {
                relic.bloodbathCharge();
            }
        }
        addToTop(new ExhaustAction(10,false));
        addToBot(new core(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL),this));
    }

}
