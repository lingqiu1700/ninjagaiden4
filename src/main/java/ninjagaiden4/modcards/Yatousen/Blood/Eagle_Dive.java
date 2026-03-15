package ninjagaiden4.modcards.Yatousen.Blood;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FlightPower;
import ninjagaiden4.helpers.AbstractNinjaAttackCard;
import ninjagaiden4.helpers.AttackHelper;
import ninjagaiden4.helpers.ModHelper;

public class Eagle_Dive extends AbstractNinjaAttackCard {
    public static final String ID = ModHelper.makeID("Eagle_Dive");

    public Eagle_Dive() {
        super(
                ID,
                "Eagle_Dive",
                2,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY,
                15,
                2,
                0.2F,
                5,
                0
        );
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ModHelper.PAP(new FlightPower(p,2));
        ModHelper.PAP(new FlightPower(p,2));

        ModHelper.Loop(0,this.magicNumber,i -> AttackHelper.coreAction(p,m,this));

        addToBot(new StunMonsterAction(m,p));
    }
}
