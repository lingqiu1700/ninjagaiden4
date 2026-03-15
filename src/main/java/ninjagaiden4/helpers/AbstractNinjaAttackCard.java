package ninjagaiden4.helpers;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import ninjagaiden4.modcore.Ninja4;

import static ninjagaiden4.characters.yakumo.PlayerColorEnum.YAKUMO_CARD_COLOR;

public abstract class AbstractNinjaAttackCard extends CustomCard {

    private int upDamage;
    private int upMagic;

    public AbstractNinjaAttackCard(
            String id,
            String imgName,
            int cost,
            CardRarity rarity,
            CardTarget target,
            int damage,
            int magicNumber,
            float dismemberRate,
            int upgradeDamage,
            int upgradeMagicNumber
    ) {
        super(
                id,
                CardCrawlGame.languagePack.getCardStrings(id).NAME,
                "lingqiu1700/ninjagaiden4/images/cards/attack/" + imgName + ".png",
                cost,
                CardCrawlGame.languagePack.getCardStrings(id).DESCRIPTION,
                CardType.ATTACK,
                YAKUMO_CARD_COLOR,
                rarity,
                target
        );

        this.baseDamage = damage;
        this.baseMagicNumber = magicNumber;
        this.magicNumber = this.baseMagicNumber;

        this.upDamage = upgradeDamage;
        this.upMagic = upgradeMagicNumber;

        if (target == CardTarget.ALL_ENEMY) {
            this.isMultiDamage = true;
        }

        CustomTag();

        this.tags.add(Ninja4.CURSED_BLADES);
        Ninja4.CardFields.dismemberRate.set(this, dismemberRate);
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();

            if (this.upDamage != 0) {
                this.upgradeMagicNumber(this.upDamage);
            }

            if (this.upMagic != 0) {
                this.upgradeMagicNumber(this.upMagic);
            }

            CustomUp();

            CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
            if (cardStrings.UPGRADE_DESCRIPTION != null) {
                this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
                this.initializeDescription();
            }
        }
    }

    public void CustomUp() {

    }

    public void CustomTag() {

    }
}
