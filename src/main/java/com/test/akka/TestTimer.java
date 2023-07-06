package com.test.akka;

import akka.actor.*;
import scala.concurrent.duration.Duration;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class TestTimer {
    private static Object TICK_KEY = "TickKey";


    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("testSystem");
        ActorRef parentNode = system.actorOf(ParentNode.props(), "parentNode");
        IntStream.range(0, 10).forEach(it -> parentNode.tell("name" + it, ActorRef.noSender()));

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(0, 5).forEach(it -> parentNode.tell("stopChild", ActorRef.noSender()));


    }

    static final class ParentNode extends AbstractActor {
        static Props props() {
            return Props.create(ParentNode.class, ParentNode::new);
        }

        LinkedList<ActorRef> actorRefList = new LinkedList<>();

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .matchEquals("stopChild", m -> {
                        ActorRef poll = actorRefList.poll();
                        if (Objects.nonNull(poll)) {
                            getContext().stop(poll);
                            //poll.tell("stop", getSelf());
                        }
                    })
                    .match(String.class, name -> {
                        ActorRef child = getContext().actorOf(ChildNode.props(), name);
                        actorRefList.add(child);
                    })
                    .build();

        }
    }

    static class ChildNode extends AbstractActorWithTimers {
        static Props props() {
            return Props.create(ChildNode.class, ChildNode::new);
        }

        int i;
        String actorName;

        @Override
        public void preStart() {
            ActorRef self = getSelf();
            actorName = self.path().name();
            char lastChar = actorName.charAt(actorName.length() - 1);
            i = Character.getNumericValue(lastChar);
            System.out.println(actorName + "  actor started , start num is " + i);
            getTimers().startTimerWithFixedDelay(TICK_KEY, i, Duration.create(1000, TimeUnit.MILLISECONDS), Duration.create(1000, TimeUnit.MILLISECONDS));
        }

        @Override
        public void postStop() {
            System.out.println(actorName + "  actor stopped , " + i);
        }

        @Override
        public Receive createReceive() {
            return receiveBuilder()
                    .match(Integer.class, num -> System.out.println(actorName + " actor  receive num is " + num))
                    .matchEquals("fail", f -> {
                        System.out.println(actorName + "  actor fails now");
                        throw new Exception(actorName + "  failed!");
                    })
                    .build();
        }
    }
}
