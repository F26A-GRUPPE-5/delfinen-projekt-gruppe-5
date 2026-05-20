package dk.delfinen.gruppe5.application.usecase;

import dk.delfinen.gruppe5.application.port.in.ViewTop5SwimmersPerDisciplineUseCase;
import dk.delfinen.gruppe5.application.port.out.MemberRepository;
import dk.delfinen.gruppe5.domain.model.Member;
import dk.delfinen.gruppe5.domain.model.TrainingResult;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ViewTop5SwimmersPerDisciplineUseCaseImpl
        implements ViewTop5SwimmersPerDisciplineUseCase {

    private final MemberRepository memberRepository;

    public ViewTop5SwimmersPerDisciplineUseCaseImpl(
            MemberRepository memberRepository) {

        this.memberRepository = memberRepository;
    }

    @Override
    public List<Member> execute(String discipline) {

        return memberRepository.findAll().stream()

                // Kun konkurrencesvømmere
                .filter(Member::isCompetitor)

                // Senior (18+)
                .filter(member -> member.getAge() >= 18)

                // Har resultater i disciplinen
                .filter(member ->
                        member.getTrainingResults().stream()
                                .anyMatch(result ->
                                        result.getDiscipline()
                                                .equalsIgnoreCase(discipline)
                                )
                )

                // Sorter efter bedste tid
                .sorted(Comparator.comparingDouble(member ->
                        bestTime(member, discipline)
                ))

                // Top 5
                .limit(5)

                .collect(Collectors.toList());
    }

    public List<Member> executeJunior(String discipline) {

        return memberRepository.findAll().stream()

                // Kun konkurrencesvømmere
                .filter(Member::isCompetitor)

                // Junior (<18)
                .filter(member -> member.getAge() < 18)

                // Har resultater i disciplinen
                .filter(member ->
                        member.getTrainingResults().stream()
                                .anyMatch(result ->
                                        result.getDiscipline()
                                                .equalsIgnoreCase(discipline)
                                )
                )

                // Sorter efter bedste tid
                .sorted(Comparator.comparingDouble(member ->
                        bestTime(member, discipline)
                ))

                // Top 5
                .limit(5)

                .collect(Collectors.toList());
    }

    private double bestTime(
            Member member,
            String discipline) {

        return member.getTrainingResults().stream()

                .filter(result ->
                        result.getDiscipline()
                                .equalsIgnoreCase(discipline)
                )

                .mapToDouble(TrainingResult::getTime)

                .min()

                .orElse(Double.MAX_VALUE);
    }
}